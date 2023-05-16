package com.example.Vaccinator.transformer;

import com.example.Vaccinator.dto.RequestDTO.CenterRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.CenterResponseDto;
import com.example.Vaccinator.model.VaccinationCenter;
import com.example.Vaccinator.service.VaccinationCenterService;

public class VaccinationCenterTransformer {

    public static VaccinationCenter CenterRequestDtoToCenter(CenterRequestDto centerRequestDto){
        return VaccinationCenter.builder()
                .name(centerRequestDto.getName())
                .location(centerRequestDto.getLocation())
                .centerType(centerRequestDto.getCenterType())
                .build();
    }

    public static CenterResponseDto CenterToCenterResponseDto(VaccinationCenter vaccinationCenter){

        return CenterResponseDto.builder()
                .name(vaccinationCenter.getName())
                .location(vaccinationCenter.getLocation())
                .centerType(vaccinationCenter.getCenterType())
                .build();
    }
}
