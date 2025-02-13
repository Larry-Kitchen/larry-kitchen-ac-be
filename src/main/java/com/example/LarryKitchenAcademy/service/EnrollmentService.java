package com.example.LarryKitchenAcademy.service;

import com.example.LarryKitchenAcademy.dto.EnrollmentDto;
import com.example.LarryKitchenAcademy.entity.Enrollment;
import com.example.LarryKitchenAcademy.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository repository;

    public Enrollment saveEnrollment(EnrollmentDto enrollment){
        if(repository.findEnrollmentByEnrollmentId(enrollment.getEnrollmentId()).isPresent()){
            throw new RuntimeException("Data already exists");
        }

        Enrollment result = repository.save(Enrollment.builder()
                        .trainingId(enrollment.getTrainingId())
                        .enrollmentStatus(enrollment.getEnrollmentStatus())
                        .userId(enrollment.getUserId())
                        .enrollmentId(enrollment.getEnrollmentId())
                        .enrollmentDate(enrollment.getEnrollmentDate())
                        .build());
        return result;
//        return EnrollmentDto.builder()
//                .userId(result.getUserId())
//                .enrollmentId(result.getEnrollmentId())
//                .enrollmentStatus(result.getEnrollmentStatus())
//                .trainingId(result.getTrainingId())
//                .enrollmentDate(result.getEnrollmentDate())
//                .build();
    }

}
