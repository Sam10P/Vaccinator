package com.example.Vaccinator.service.impl;

import com.example.Vaccinator.Enum.DoseNo;
import com.example.Vaccinator.dto.RequestDTO.AppointmentRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Vaccinator.dto.ResponseDTO.CenterResponseDto;
import com.example.Vaccinator.dto.ResponseDTO.UserResponseDto;
import com.example.Vaccinator.exception.DoctorNotFoundException;
import com.example.Vaccinator.exception.Dose1NotTakenException;
import com.example.Vaccinator.exception.UserNotFoundException;
import com.example.Vaccinator.model.*;
import com.example.Vaccinator.repository.AppointmentRepository;
import com.example.Vaccinator.repository.DoctorRepository;
import com.example.Vaccinator.repository.UserRepository;
import com.example.Vaccinator.service.AppointmentService;
import com.example.Vaccinator.service.Dose1Service;
import com.example.Vaccinator.service.Dose2Service;
import com.example.Vaccinator.service.UserService;
import com.example.Vaccinator.transformer.AppointmentTransformer;
import com.example.Vaccinator.transformer.VaccinationCenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.net.UnknownServiceException;
import java.util.Optional;
import java.util.UUID;

import static java.awt.SystemColor.text;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    Dose1Service dose1Service;

    @Autowired
    Dose2Service dose2Service;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws DoctorNotFoundException, UserNotFoundException, Dose1NotTakenException {

        Optional<User> optionalUser = userRepository.findById(appointmentRequestDto.getUserId());
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User doesn't exist!");
        }

        Optional<Doctor> optionalDoctor = doctorRepository.findById((appointmentRequestDto.getDoctorId()));
        if(!optionalDoctor.isPresent()){
            throw new DoctorNotFoundException("Doctor doesn't exist!");
        }

        User user = optionalUser.get();
        Doctor doctor = optionalDoctor.get();

        if(appointmentRequestDto.getDoseNo() == DoseNo.DOSE_1){
            Dose1 dose1 = dose1Service.createDose1(user, appointmentRequestDto.getVaccineType());
            user.setDose1Taken(true);
            user.setDose1(dose1);
        }
        else{
            if(user.isDose1Taken() == true){
                Dose2 dose2 = dose2Service.createDose2(user, appointmentRequestDto.getVaccineType());
                user.setDose2Taken(true);
                user.setDose2(dose2);
            }
            else{
                throw new Dose1NotTakenException("Please take Dose1 first!");
            }
        }

        Appointment appointment = AppointmentTransformer.AppointmentRequestDtoToAppointment(appointmentRequestDto);
        appointment.setAppointmentNo(String.valueOf(UUID.randomUUID()));
        appointment.setUser(user);
        appointment.setDoctor(doctor);

        // add appointment to the doctor's appointment list
        doctor.getAppointments().add(appointment);
        user.getAppointments().add(appointment);

        // add appointment to DB
        Appointment savedAppointment = appointmentRepository.save(appointment);

        // send email

        String text = "Congrats!!! " + user.getName() + " Your dose " + appointmentRequestDto.getDoseNo() + " has been booked.";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vaccinatorproject@gmail.com");
        message.setTo(user.getEmailId());
        message.setSubject("Appointment Booked!!!");
        message.setText(text);
        emailSender.send(message);

        AppointmentResponseDto appointmentResponseDto = AppointmentTransformer.AppointmentToAppointmentResponseDto(savedAppointment, user, doctor);
        appointmentResponseDto.setVaccineType(appointmentRequestDto.getVaccineType());
        // getting the center from doctor and transforming to centerResponseDto
        appointmentResponseDto.setCenterResponseDto(VaccinationCenterTransformer.CenterToCenterResponseDto(doctor.getVaccinationCenter()));

        return appointmentResponseDto;
    }
}
