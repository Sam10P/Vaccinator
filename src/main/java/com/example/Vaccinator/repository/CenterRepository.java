package com.example.Vaccinator.repository;

import com.example.Vaccinator.Enum.CenterType;
import com.example.Vaccinator.model.Doctor;
import com.example.Vaccinator.model.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterRepository extends JpaRepository<VaccinationCenter, Integer> {

//    @Query(value = "SELECT * FROM center WHERE id = : id", nativeQuery = true)
//    VaccinationCenter findById(int id);

    List<VaccinationCenter> findByCenterType(CenterType centerType);
}
