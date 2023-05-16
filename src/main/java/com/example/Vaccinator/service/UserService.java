package com.example.Vaccinator.service;

import com.example.Vaccinator.dto.RequestDTO.UserRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.UserDetailsDto;
import com.example.Vaccinator.dto.ResponseDTO.UserListDto;
import com.example.Vaccinator.dto.ResponseDTO.UserResponseDto;
import com.example.Vaccinator.model.User;
import com.example.Vaccinator.repository.UserRepository;

import java.util.List;

public interface UserService {

    public UserResponseDto addUser(UserRequestDto userRequestDto);

    public UserDetailsDto findByEmail(String emailId);

    public UserResponseDto updateNameByMobNo(String mobNo, String name);

    public UserListDto usersNotTakenAnyDose();

    public UserListDto usersHaveTakenOnlyDose1();

    public UserListDto usersFullyVaccinated();

    public UserListDto maleWithZeroDose();

    public UserListDto femaleFullyVaccinated();

}
