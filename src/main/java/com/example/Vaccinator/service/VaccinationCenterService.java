package com.example.Vaccinator.service;

import com.example.Vaccinator.Enum.CenterType;
import com.example.Vaccinator.dto.RequestDTO.CenterRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.CenterResponseDto;
import com.example.Vaccinator.exception.CenterNotPresentException;

import java.util.List;

public interface VaccinationCenterService {

    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto);

    public List<String> doctorsAtCenter(int id) throws CenterNotPresentException;

    public List<String> maleDoctorsAtCenter(int id) throws CenterNotPresentException;

    public List<String> femaleDoctorsAtCenter(int id) throws CenterNotPresentException;

    public List<String> maleDoctorsAboveAgeAtCenter(int id, int age) throws CenterNotPresentException;

    public List<String> allCenterOfACenterType(CenterType centerType);
}
