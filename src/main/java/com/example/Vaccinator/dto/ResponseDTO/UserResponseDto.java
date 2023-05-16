package com.example.Vaccinator.dto.ResponseDTO;

import com.example.Vaccinator.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponseDto {

    String name;

    String message;

}

