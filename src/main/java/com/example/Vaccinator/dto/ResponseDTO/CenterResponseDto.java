package com.example.Vaccinator.dto.ResponseDTO;


import com.example.Vaccinator.Enum.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CenterResponseDto {

    String name;

    String location;

    CenterType centerType;

}
