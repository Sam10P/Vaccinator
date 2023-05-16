package com.example.Vaccinator.controller;

import com.example.Vaccinator.dto.RequestDTO.UserRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.UserDetailsDto;
import com.example.Vaccinator.dto.ResponseDTO.UserListDto;
import com.example.Vaccinator.dto.ResponseDTO.UserResponseDto;
import com.example.Vaccinator.model.User;
import com.example.Vaccinator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){

        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    // find by emailId
    @GetMapping("/find-by-email/{emailId}")
    public ResponseEntity findByEmail(@PathVariable String emailId){

        UserDetailsDto userDetailsDto = userService.findByEmail(emailId);
        return new ResponseEntity<>(userDetailsDto, HttpStatus.FOUND);
    }

    // update the name of the user based on mobno
    @PutMapping("/update-name-by-mobNo")
    public ResponseEntity updateNameByMobNo(@RequestParam String mobNo, @RequestParam String name){

        UserResponseDto userResponseDto = userService.updateNameByMobNo(mobNo, name);
        return new ResponseEntity<>(userResponseDto, HttpStatus.ACCEPTED);
    }

    // all the users who have not taken even a single dose
    @GetMapping("/users-havenot-taken-any-dose")
    public ResponseEntity usersNotTakenAnyDose(){

        UserListDto userListDto = userService.usersNotTakenAnyDose();
        return new ResponseEntity<>(userListDto, HttpStatus.FOUND);
    }

    // all users who have taken dose1 but not dose2
    @GetMapping("/users-have-taken-only-dose1")
    public ResponseEntity usersHaveTakenAnyDose1(){

        UserListDto userListDto = userService.usersHaveTakenOnlyDose1();
        return new ResponseEntity<>(userListDto, HttpStatus.FOUND);
    }

    // all users who are fully vaccinated
    @GetMapping("/users-fully-vaccinated")
    public ResponseEntity usersFullyVaccinated(){

        UserListDto userListDto = userService.usersFullyVaccinated();
        return new ResponseEntity<>(userListDto, HttpStatus.FOUND);
    }

    // all male users who have not taken even a single dose
    @GetMapping("/male-users-with-zero-dose")
    public ResponseEntity maleWithZeroDose(){

        UserListDto userListDto = userService.maleWithZeroDose();
        return new ResponseEntity<>(userListDto, HttpStatus.FOUND);
    }

    // all female users who are fully vaccinated
    @GetMapping("/female-users-fully-vaccinated")
    public ResponseEntity femaleFullyVaccinated(){

        UserListDto userListDto = userService.femaleFullyVaccinated();
        return new ResponseEntity<>(userListDto, HttpStatus.FOUND);
    }
}
