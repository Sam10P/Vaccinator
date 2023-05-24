package com.example.Vaccinator.controller;

import com.example.Vaccinator.Enum.CenterType;
import com.example.Vaccinator.VaccinatorApplication;
import com.example.Vaccinator.dto.RequestDTO.CenterRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.CenterResponseDto;
import com.example.Vaccinator.exception.CenterNotPresentException;
import com.example.Vaccinator.model.VaccinationCenter;
import com.example.Vaccinator.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @PostMapping("/add")
    public ResponseEntity addVaccinationCenter(@RequestBody CenterRequestDto centerRequestDto){

        CenterResponseDto centerResponseDto = vaccinationCenterService.addCenter(centerRequestDto);
        return new ResponseEntity(centerResponseDto, HttpStatus.CREATED);
    }

    // give the list of all doctors at a particular center(center id)
    @GetMapping("/doctors-at-center/{id}")
    public ResponseEntity doctorsAtCenter(@PathVariable int id) throws CenterNotPresentException {

        try {
            List<String> ans = vaccinationCenterService.doctorsAtCenter(id);
            return new ResponseEntity(ans, HttpStatus.FOUND);
        }
        catch (CenterNotPresentException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    // give the list of all male doctors at a particular center(center id)
    @GetMapping("/male-doctors-at-center/{id}")
    public ResponseEntity maleDoctorsAtCenter(@PathVariable int id) throws CenterNotPresentException {

        try {
            List<String> ans = vaccinationCenterService.maleDoctorsAtCenter(id);
            return new ResponseEntity(ans, HttpStatus.FOUND);
        }
        catch (CenterNotPresentException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // give the list of all female doctors at a particular center(center id)
    @GetMapping("/female-doctors-at-center/{id}")
    public ResponseEntity femaleDoctorsAtCenter(@PathVariable int id) throws CenterNotPresentException {

        try {
            List<String> ans = vaccinationCenterService.femaleDoctorsAtCenter(id);
            return new ResponseEntity(ans, HttpStatus.FOUND);
        }
        catch (CenterNotPresentException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // give the list of all male doctors above age 40 at a particular center(center id)
    @GetMapping("/male-doctors-at-center/id/{id}/age/{age}")
    public ResponseEntity maleDoctorsAboveAgeAtCenter(@PathVariable int id, @PathVariable int age) throws CenterNotPresentException {

        try {
            List<String> ans = vaccinationCenterService.maleDoctorsAboveAgeAtCenter(id, age);
            return new ResponseEntity(ans, HttpStatus.FOUND);
        }
        catch (CenterNotPresentException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // give all center of a particular centertype
    @GetMapping("/all-centers-of-a-centertype")
    public ResponseEntity allCenterOfACenterType(@RequestParam CenterType centerType){

        List<String> ans = vaccinationCenterService.allCenterOfACenterType(centerType);
        return new ResponseEntity(ans, HttpStatus.FOUND);
    }
}
