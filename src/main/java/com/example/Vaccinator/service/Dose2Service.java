package com.example.Vaccinator.service;

import com.example.Vaccinator.Enum.VaccineType;
import com.example.Vaccinator.model.Dose1;
import com.example.Vaccinator.model.Dose2;
import com.example.Vaccinator.model.User;

public interface Dose2Service {

    public Dose2 createDose2(User user, VaccineType vaccineType);
}
