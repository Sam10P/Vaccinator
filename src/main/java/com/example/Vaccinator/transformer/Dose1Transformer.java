package com.example.Vaccinator.transformer;

import com.example.Vaccinator.Enum.VaccineType;
import com.example.Vaccinator.model.Dose1;
import com.example.Vaccinator.model.User;
import com.example.Vaccinator.model.VaccinationCenter;

import java.util.UUID;

public class Dose1Transformer {

    public static Dose1 createDose1(User user, VaccineType vaccineType){

        return Dose1.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .vaccineType(vaccineType)
                .user(user)
                .build();
    }
}
