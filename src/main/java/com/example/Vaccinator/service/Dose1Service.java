package com.example.Vaccinator.service;

import com.example.Vaccinator.Enum.VaccineType;
import com.example.Vaccinator.model.Dose1;
import com.example.Vaccinator.model.User;

public interface Dose1Service {

    public Dose1 createDose1(User user, VaccineType vaccineType);

}
