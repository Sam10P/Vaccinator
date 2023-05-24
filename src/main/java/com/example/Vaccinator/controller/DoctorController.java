package com.example.Vaccinator.controller;

import com.example.Vaccinator.dto.RequestDTO.DoctorRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.DoctorResponseDto;
import com.example.Vaccinator.exception.CenterNotPresentException;
import com.example.Vaccinator.model.Doctor;
import com.example.Vaccinator.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){

        try{
            DoctorResponseDto doctorResponseDto = doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity(doctorResponseDto, HttpStatus.CREATED);
        }
        catch (CenterNotPresentException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    // get all the doctors who have more than ten appointments
    @GetMapping("/more-than-10-appointments")
    public ResponseEntity doctorMoreThan10Appointments(){
        List<String> ans = doctorService.doctorMoreThan10Appointments();
        return new ResponseEntity(ans, HttpStatus.FOUND);
    }

    // get all the male doctors whose age is above 40
    @GetMapping("/male-age-more-than-40")
    public ResponseEntity maleAgeMoreThan40(){
        List<String> ans = doctorService.maleAgeMoreThan40();
        return new ResponseEntity(ans, HttpStatus.FOUND);
    }

    // get the ratio of male to female doctors
    @GetMapping("/male-to-female-ratio")
    public ResponseEntity maleToFemaleRatio(){
        String ans = doctorService.maleToFemaleRatio();
        return new ResponseEntity(ans, HttpStatus.FOUND);
    }
    // update the details based on email id of the doctor
    @PutMapping("/update-by-email_id")
    public ResponseEntity updateByEmailId(@RequestParam String emailId, @RequestParam String name){
        String ans = doctorService.updateByEmailId(emailId, name);
        return new ResponseEntity(ans, HttpStatus.ACCEPTED);
    }
}
