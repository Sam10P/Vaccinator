package com.example.Vaccinator.transformer;

import com.example.Vaccinator.dto.RequestDTO.UserRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.UserResponseDto;
import com.example.Vaccinator.model.User;
import lombok.Builder;

public class UserTransformer {

    public static User UserRequestDtoToUser(UserRequestDto userRequestDto){

        return User.builder()
                .name(userRequestDto.getName())
                .age(userRequestDto.getAge())
                .emailId(userRequestDto.getEmailId())
                .gender(userRequestDto.getGender())
                .mobNo(userRequestDto.getMobNo())
                .build();
    }

    public static UserResponseDto UserToUserResponseDto(User user){

        return UserResponseDto.builder()
                .name(user.getName())
                .message("Congrats! You have registered on Vaccinator Successfully")
                .build();
    }
}
