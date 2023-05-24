package com.example.Vaccinator.repository;

import com.example.Vaccinator.model.Doctor;
import com.example.Vaccinator.model.Dose1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Dose1Repository extends JpaRepository<Dose1, Integer> {

    @Query(value = "SELECT * FROM doctor WHERE email_id = :emailId", nativeQuery = true)
    List<Doctor> findByEmailId(String emailId);
}
