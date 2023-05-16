package com.example.Vaccinator.service.impl;

import com.example.Vaccinator.Enum.VaccineType;
import com.example.Vaccinator.model.Dose1;
import com.example.Vaccinator.model.User;
import com.example.Vaccinator.repository.Dose1Repository;
import com.example.Vaccinator.service.Dose1Service;
import com.example.Vaccinator.transformer.Dose1Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Dose1ServiceImpl implements Dose1Service {

    @Autowired
    Dose1Repository dose1Repository;

    @Override
    public Dose1 createDose1(User user, VaccineType vaccineType) {

        Dose1 dose1 = Dose1Transformer.createDose1(user, vaccineType);

        Dose1 savedDose1 = dose1Repository.save(dose1);

        return savedDose1;
    }
}
