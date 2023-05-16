package com.example.Vaccinator.service.impl;

import com.example.Vaccinator.dto.RequestDTO.CenterRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.CenterResponseDto;
import com.example.Vaccinator.model.VaccinationCenter;
import com.example.Vaccinator.repository.CenterRepository;
import com.example.Vaccinator.service.VaccinationCenterService;
import com.example.Vaccinator.transformer.VaccinationCenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CenterServiceImpl implements VaccinationCenterService {

    @Autowired
    CenterRepository centerRepository;

    @Override
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {

        // request dto -> entity
        VaccinationCenter vaccinationCenter = VaccinationCenterTransformer.CenterRequestDtoToCenter(centerRequestDto);

        // save to your DB
        VaccinationCenter savedCenter = centerRepository.save(vaccinationCenter);

        // entity -> response dto
        return VaccinationCenterTransformer.CenterToCenterResponseDto(savedCenter);
    }
}
