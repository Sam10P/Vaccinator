package com.example.Vaccinator.service.impl;


import com.example.Vaccinator.dto.RequestDTO.DoctorRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.CenterResponseDto;
import com.example.Vaccinator.dto.ResponseDTO.DoctorResponseDto;
import com.example.Vaccinator.exception.CenterNotPresentException;
import com.example.Vaccinator.model.Doctor;
import com.example.Vaccinator.model.VaccinationCenter;
import com.example.Vaccinator.repository.CenterRepository;
import com.example.Vaccinator.service.DoctorService;
import com.example.Vaccinator.transformer.DoctorTransformer;
import com.example.Vaccinator.transformer.VaccinationCenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    CenterRepository centerRepository;


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
}
