package com.example.Vaccinator.repository;

import com.example.Vaccinator.model.Doctor;
import com.example.Vaccinator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

//    @Query(value = "SELECT * FROM user WHERE email_id = :emailId", nativeQuery = true)
//    User findByEmail(String emailId);

    @Query(value = "SELECT * FROM doctor", nativeQuery = true)
    List<Doctor> doctorMoreThan10Appointments();

    @Query(value = "SELECT * FROM doctor WHERE gender = 'MALE' AND age >= 40", nativeQuery = true)
    List<Doctor> maleAgeMoreThan40();

    @Query(value = "SELECT COUNT(id) FROM doctor WHERE gender = :gender", nativeQuery = true)
    int getDoctorByGender(String gender);

    @Query(value = "SELECT * FROM doctor WHERE email_id = :emailId", nativeQuery = true)
    Doctor findByEmailId(String emailId);
}
