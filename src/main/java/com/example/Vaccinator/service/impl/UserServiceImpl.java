package com.example.Vaccinator.service.impl;

import com.example.Vaccinator.dto.RequestDTO.UserRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.UserDetailsDto;
import com.example.Vaccinator.dto.ResponseDTO.UserListDto;
import com.example.Vaccinator.dto.ResponseDTO.UserResponseDto;
import com.example.Vaccinator.model.User;
import com.example.Vaccinator.repository.UserRepository;
import com.example.Vaccinator.service.UserService;
import com.example.Vaccinator.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {

//        convert request dto to entitiy
        User user = UserTransformer.UserRequestDtoToUser(userRequestDto);

//        save the object in db
        User savedUser = userRepository.save(user);

//        entitiy -> response dto
//        UserResponseDto userResponseDto = new UserResponseDto();
//        userResponseDto.setName(savedUser.getName());
//        userResponseDto.setMessage("Congrats! You have successfully registered on Vaccinator");
        UserResponseDto userResponseDto = UserTransformer.UserToUserResponseDto(savedUser);

        return userResponseDto;
    }

    public UserDetailsDto findByEmail(String emailId){

        User user = userRepository.findByEmail(emailId);

//        entity -> response dto
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setName(user.getName());
        userDetailsDto.setAge(user.getAge());
        userDetailsDto.setGender(user.getGender());
        if(user.isDose1Taken() == false){
            userDetailsDto.setIsDose1Taken("NO");
        }else{
            userDetailsDto.setIsDose1Taken("YES");
        }
        if(user.isDose2Taken() == false){
            userDetailsDto.setIsDose2Taken("NO");
        }else{
            userDetailsDto.setIsDose2Taken("YES");
        }

        return userDetailsDto;

    }

    public UserResponseDto updateNameByMobNo(String mobNo, String name){

        // find the user by mobNo
        User updatedUser = userRepository.findByMobNo(mobNo);
        // update the name of user
        updatedUser.setName(name);
        // save the details in database
        User savedUser = userRepository.save(updatedUser);

        // entity -> response dto
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setMessage("Congrats! You have successfully updated your name");

        return userResponseDto;

    }

    public UserListDto usersNotTakenAnyDose(){

        List<User> list = userRepository.usersNotTakenAnyDose();
        List<String> ans = new ArrayList<>();
        for(User user: list){
            ans.add(user.getName());
        }

        UserListDto userListDto = new UserListDto();
        userListDto.setList(ans);
        userListDto.setMessage("Hurray! Here is the List of users who have not taken any vaccine doses");

        return userListDto;
    }

    public UserListDto usersHaveTakenOnlyDose1(){

        List<User> list = userRepository.usersHaveTakenOnlyDose1();
        List<String> ans = new ArrayList<>();
        for(User user: list){
            ans.add(user.getName());
        }

        UserListDto userListDto = new UserListDto();
        userListDto.setList(ans);
        userListDto.setMessage("Hurray! Here is the List of users who have taken only Dose 1");

        return userListDto;
    }

    public UserListDto usersFullyVaccinated(){

        List<User> list = userRepository.usersFullyVaccinated();
        List<String> ans = new ArrayList<>();
        for(User user: list){
            ans.add(user.getName());
        }

        UserListDto userListDto = new UserListDto();
        userListDto.setList(ans);
        userListDto.setMessage("Hurray! Here is the List of users who are fully vaccinated");

        return userListDto;
    }

    public UserListDto maleWithZeroDose(){

        List<User> list = userRepository.maleWithZeroDose();
        List<String> ans = new ArrayList<>();
        for(User user: list){
            ans.add(user.getName());
        }

        UserListDto userListDto = new UserListDto();
        userListDto.setList(ans);
        userListDto.setMessage("Hurray! Here is the List of Male users who have not taken any dose");

        return userListDto;
    }

    public UserListDto femaleFullyVaccinated(){

        List<User> list = userRepository.femaleFullyVaccinated();
        List<String> ans = new ArrayList<>();
        for(User user: list){
            ans.add(user.getName());
        }

        UserListDto userListDto = new UserListDto();
        userListDto.setList(ans);
        userListDto.setMessage("Hurray! Here is the List of Female users who are fully Vaccinated");

        return userListDto;
    }



}
