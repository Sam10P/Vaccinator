package com.example.Vaccinator.service;

import com.example.Vaccinator.dto.RequestDTO.AppointmentRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Vaccinator.exception.DoctorNotFoundException;
import com.example.Vaccinator.exception.Dose1NotTakenException;
import com.example.Vaccinator.exception.UserNotFoundException;

import java.net.UnknownServiceException;

public interface AppointmentService {

    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws DoctorNotFoundException, UserNotFoundException, Dose1NotTakenException;

}
