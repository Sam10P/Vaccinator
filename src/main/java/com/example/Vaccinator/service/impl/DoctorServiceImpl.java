package com.example.Vaccinator.service.impl;


import com.example.Vaccinator.dto.RequestDTO.DoctorRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.CenterResponseDto;
import com.example.Vaccinator.dto.ResponseDTO.DoctorListDto;
import com.example.Vaccinator.dto.ResponseDTO.DoctorResponseDto;
import com.example.Vaccinator.exception.CenterNotPresentException;
import com.example.Vaccinator.model.Doctor;
import com.example.Vaccinator.model.VaccinationCenter;
import com.example.Vaccinator.repository.CenterRepository;
import com.example.Vaccinator.repository.DoctorRepository;
import com.example.Vaccinator.service.DoctorService;
import com.example.Vaccinator.transformer.DoctorTransformer;
import com.example.Vaccinator.transformer.VaccinationCenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    CenterRepository centerRepository;

    @Autowired
    DoctorRepository doctorRepository;


    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException {

       Optional<VaccinationCenter> optionalCenter = centerRepository.findById(doctorRequestDto.getCenterId());
       if(!optionalCenter.isPresent()){
            throw new CenterNotPresentException("Invalid center Id!");
       }

       VaccinationCenter center = optionalCenter.get();

       // dto -> entity
        Doctor doctor = DoctorTransformer.DoctorRequestDtoToDoctor(doctorRequestDto);
        doctor.setVaccinationCenter(center);

        // add doctor to current list of doctors at that center
        center.getDoctors().add(doctor);

        VaccinationCenter savedCenter = centerRepository.save(center); // saves both center and doctor

        //prepare response Dto
        CenterResponseDto centerResponseDto = VaccinationCenterTransformer.CenterToCenterResponseDto(savedCenter);

        return DoctorTransformer.DoctorToDoctorResponseDto(doctor);
    }

    public List<String> doctorMoreThan10Appointments(){

        List<Doctor> list = doctorRepository.doctorMoreThan10Appointments();

        List<String> ans = new ArrayList<>();
        for(Doctor doctor : list){
            if(doctor.getAppointments().size() >= 10){
                ans.add(doctor.getName());
            }
        }
            return ans;
    }

    public List<String> maleAgeMoreThan40(){

        List<Doctor> list = doctorRepository.maleAgeMoreThan40();

        List<String> ans = new ArrayList<>();
        for(Doctor doctor : list){
                ans.add(doctor.getName());
        }
        return ans;
    }

    public String maleToFemaleRatio(){
        int maleDoctors = doctorRepository.getDoctorByGender("MALE");
        int femaleDoctors = doctorRepository.getDoctorByGender("FEMALE");

        int percentOfMale = (100*maleDoctors)/(maleDoctors+femaleDoctors);
        int percentOfFemale = (100*femaleDoctors)/(maleDoctors+femaleDoctors);

        String ans = percentOfMale + "% are Male Doctors and " + percentOfFemale + "% are Female Doctors.";

        return ans;
    }

    public String updateByEmailId(String emailId, String name){
        
        Doctor updateDoctor = doctorRepository.findByEmailId(emailId);
        updateDoctor.setName(name);
        Doctor savedDoctor = doctorRepository.save(updateDoctor);

        String ans = "The name of doctor with ID " + savedDoctor.getId() + " and emailID " + savedDoctor.getEmailId() + " has been updated to " + savedDoctor.getName() + ".";
        return ans;
    }

}
