package com.example.Vaccinator.dto.ResponseDTO;

import com.example.Vaccinator.Enum.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsDto {

    String name;

    int age;

    Gender gender;

    String isDose1Taken;

    String isDose2Taken;
}
