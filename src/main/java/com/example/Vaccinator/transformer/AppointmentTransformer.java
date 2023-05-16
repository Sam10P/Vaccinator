package com.example.Vaccinator.transformer;

import com.example.Vaccinator.dto.RequestDTO.AppointmentRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Vaccinator.model.Appointment;
import com.example.Vaccinator.model.Doctor;
import com.example.Vaccinator.model.User;

public class AppointmentTransformer {

    public static Appointment AppointmentRequestDtoToAppointment(AppointmentRequestDto appointmentRequestDto){

        return Appointment.builder()
                .doseNo(appointmentRequestDto.getDoseNo())
                .build();
    }

    public static AppointmentResponseDto AppointmentToAppointmentResponseDto(Appointment appointment, User user, Doctor doctor){

        return AppointmentResponseDto.builder()
                .userName(user.getName())
                .appointmentNo(appointment.getAppointmentNo())
                .dateOfAppointment(appointment.getDateOfAppointment())
                .doseNo(appointment.getDoseNo())
                .doctorName(doctor.getName())
                .build();
    }
}
