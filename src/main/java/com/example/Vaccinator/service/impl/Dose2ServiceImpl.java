package com.example.Vaccinator.service.impl;

import com.example.Vaccinator.Enum.VaccineType;
import com.example.Vaccinator.model.Dose1;
import com.example.Vaccinator.model.Dose2;
import com.example.Vaccinator.model.User;
import com.example.Vaccinator.repository.Dose2Repository;
import com.example.Vaccinator.service.Dose2Service;
import com.example.Vaccinator.transformer.Dose1Transformer;
import com.example.Vaccinator.transformer.Dose2Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Dose2ServiceImpl implements Dose2Service {

    @Autowired
    Dose2Repository dose2Repository;

    @Override
    public Dose2 createDose2(User user, VaccineType vaccineType) {

        Dose2 dose2 = Dose2Transformer.createDose2(user, vaccineType);

        Dose2 savedDose2 = dose2Repository.save(dose2);

        return savedDose2;
    }
}
