package com.example.Vaccinator.transformer;

import com.example.Vaccinator.dto.RequestDTO.DoctorRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.CenterResponseDto;
import com.example.Vaccinator.dto.ResponseDTO.DoctorResponseDto;
import com.example.Vaccinator.model.Doctor;
import com.example.Vaccinator.model.VaccinationCenter;

import java.util.List;

public class DoctorTransformer {

    public static Doctor DoctorRequestDtoToDoctor(DoctorRequestDto doctorRequestDto) {

        return Doctor.builder()
                .name(doctorRequestDto.getName())
                .age(doctorRequestDto.getAge())
                .mobNo(doctorRequestDto.getMobNo())
                .gender(doctorRequestDto.getGender())
                .emailId(doctorRequestDto.getEmailId())
                .build();
    }

    public static DoctorResponseDto DoctorToDoctorResponseDto(Doctor doctor) {

        CenterResponseDto centerResponseDto = VaccinationCenterTransformer.CenterToCenterResponseDto(doctor.getVaccinationCenter());

        return DoctorResponseDto.builder()
                .name(doctor.getName())
                .emailId(doctor.getEmailId())
                .mobNo(doctor.getMobNo())
                .centerResponseDto(centerResponseDto)
                .build();
    }

}

