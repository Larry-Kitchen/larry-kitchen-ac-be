package com.example.LarryKitchenAcademy.service;

import com.example.LarryKitchenAcademy.dto.EnrollmentDto;
import com.example.LarryKitchenAcademy.entity.Enrollment;
import com.example.LarryKitchenAcademy.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository repository;

    public Enrollment saveEnrollment(EnrollmentDto enrollment){
        if(repository.findEnrollmentByEnrollmentId(enrollment.getEnrollmentId()).isPresent()){
            throw new RuntimeException("Data already exists");
        }
        Enrollment request =  Enrollment.builder()
                .userId(enrollment.getUserId())
                .enrollmentId(enrollment.getEnrollmentId())
                .enrollmentStatus(enrollment.getEnrollmentStatus())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .build();
        return repository.save(request);
    }

    public Enrollment findEnrollmentByEnrollmentIdAndUserId(int enrollmentId, int user_id){
        return  repository.findEnrollmentByEnrollmentIdAndUserId(enrollmentId, user_id);
    }


}
