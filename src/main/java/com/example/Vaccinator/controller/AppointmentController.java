package com.example.Vaccinator.controller;

import com.example.Vaccinator.dto.RequestDTO.AppointmentRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Vaccinator.exception.DoctorNotFoundException;
import com.example.Vaccinator.exception.Dose1NotTakenException;
import com.example.Vaccinator.exception.UserNotFoundException;
import com.example.Vaccinator.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownServiceException;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto){

        try{
            AppointmentResponseDto appointmentResponseDto = appointmentService.bookAppointment(appointmentRequestDto);
            return new ResponseEntity(appointmentResponseDto, HttpStatus.CREATED);
        }
        catch (UserNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (DoctorNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Dose1NotTakenException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    // generate certificate
    // - one dose
    // - both dose
}
