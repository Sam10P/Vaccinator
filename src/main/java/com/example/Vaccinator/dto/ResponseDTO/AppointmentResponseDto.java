package com.example.Vaccinator.dto.ResponseDTO;

import com.example.Vaccinator.Enum.DoseNo;
import com.example.Vaccinator.Enum.VaccineType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AppointmentResponseDto {

    String userName;

    String appointmentNo;

    Date dateOfAppointment;

    DoseNo doseNo;

    CenterResponseDto centerResponseDto;

    String doctorName;

    VaccineType vaccineType;
}
