package com.example.Vaccinator.service;

import com.example.Vaccinator.dto.RequestDTO.DoctorRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.DoctorListDto;
import com.example.Vaccinator.dto.ResponseDTO.DoctorResponseDto;
import com.example.Vaccinator.dto.ResponseDTO.UserListDto;
import com.example.Vaccinator.exception.CenterNotPresentException;
import com.example.Vaccinator.model.Doctor;

import java.util.List;

public interface DoctorService {

    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException;
    public List<String> doctorMoreThan10Appointments();

    public List<String> maleAgeMoreThan40();

    public String maleToFemaleRatio();

    public String updateByEmailId(String emailId, String name);
}
