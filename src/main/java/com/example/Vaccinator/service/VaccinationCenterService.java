package com.example.Vaccinator.service;

import com.example.Vaccinator.dto.RequestDTO.CenterRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.CenterResponseDto;

public interface VaccinationCenterService {

    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto);

}
