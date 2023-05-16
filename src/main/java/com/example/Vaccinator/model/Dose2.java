package com.example.Vaccinator.model;


import com.example.Vaccinator.Enum.VaccineType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Dose2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "dose_id")
    String doseId;

    @Column(name = "vaccine_type")
    VaccineType vaccineType;

    @CreationTimestamp
    @Column(name = "vaccination_date")
    Date vaccinationDate;

    @OneToOne
    @JoinColumn
    User user;
}
