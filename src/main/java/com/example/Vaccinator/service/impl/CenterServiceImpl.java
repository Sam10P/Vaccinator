package com.example.Vaccinator.service.impl;

import com.example.Vaccinator.Enum.CenterType;
import com.example.Vaccinator.Enum.Gender;
import com.example.Vaccinator.dto.RequestDTO.CenterRequestDto;
import com.example.Vaccinator.dto.ResponseDTO.CenterResponseDto;
import com.example.Vaccinator.exception.CenterNotPresentException;
import com.example.Vaccinator.model.Doctor;
import com.example.Vaccinator.model.VaccinationCenter;
import com.example.Vaccinator.repository.CenterRepository;
import com.example.Vaccinator.service.VaccinationCenterService;
import com.example.Vaccinator.transformer.VaccinationCenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CenterServiceImpl implements VaccinationCenterService {

    @Autowired
    CenterRepository centerRepository;

    @Override
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {

        // request dto -> entity
        VaccinationCenter vaccinationCenter = VaccinationCenterTransformer.CenterRequestDtoToCenter(centerRequestDto);

        // save to your DB
        VaccinationCenter savedCenter = centerRepository.save(vaccinationCenter);

        // entity -> response dto
        return VaccinationCenterTransformer.CenterToCenterResponseDto(savedCenter);
    }

    @Override
    public List<String> doctorsAtCenter(int id) throws CenterNotPresentException {

        Optional<VaccinationCenter> optionalCenter = centerRepository.findById(id);
        if(!optionalCenter.isPresent()){
            throw new CenterNotPresentException("Invalid center Id!");
        }

        VaccinationCenter center = optionalCenter.get();

        List<Doctor> doctors = center.getDoctors();

        List<String> ans = new ArrayList<>();
        for(Doctor doctor : doctors){
            ans.add(doctor.getName());
        }
        return ans;
    }

    @Override
    public List<String> maleDoctorsAtCenter(int id) throws CenterNotPresentException {

        Optional<VaccinationCenter> optionalCenter = centerRepository.findById(id);
        if(!optionalCenter.isPresent()){
            throw new CenterNotPresentException("Invalid center Id!");
        }

        VaccinationCenter center = optionalCenter.get();

        List<Doctor> doctors = center.getDoctors();

        List<String> ans = new ArrayList<>();
        for(Doctor doctor : doctors){
            if(doctor.getGender().equals(Gender.MALE)){
                ans.add(doctor.getName());
            }
        }
        return ans;
    }

    @Override
    public List<String> femaleDoctorsAtCenter(int id) throws CenterNotPresentException {

        Optional<VaccinationCenter> optionalCenter = centerRepository.findById(id);
        if(!optionalCenter.isPresent()){
            throw new CenterNotPresentException("Invalid center Id!");
        }

        VaccinationCenter center = optionalCenter.get();

        List<Doctor> doctors = center.getDoctors();

        List<String> ans = new ArrayList<>();
        for(Doctor doctor : doctors){
            if(doctor.getGender().equals(Gender.FEMALE)){
                ans.add(doctor.getName());
            }
        }
        return ans;
    }

    @Override
    public List<String> maleDoctorsAboveAgeAtCenter(int id, int age) throws CenterNotPresentException {

        Optional<VaccinationCenter> optionalCenter = centerRepository.findById(id);
        if(!optionalCenter.isPresent()){
            throw new CenterNotPresentException("Invalid center Id!");
        }

        VaccinationCenter center = optionalCenter.get();

        List<Doctor> doctors = center.getDoctors();

        List<String> ans = new ArrayList<>();
        for(Doctor doctor : doctors){
            if(doctor.getGender().equals(Gender.MALE) && doctor.getAge() > age){
                ans.add(doctor.getName());
            }
        }
        return ans;
    }

    @Override
    public List<String> allCenterOfACenterType(CenterType centerType) {
        List<VaccinationCenter> centers = centerRepository.findByCenterType(centerType);

        List<String> ans = new ArrayList<>();
        for(VaccinationCenter center : centers){
            ans.add(center.getName());
        }
        return ans;
    }
}
