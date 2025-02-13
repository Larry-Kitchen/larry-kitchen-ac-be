package com.example.LarryKitchenAcademy.controller;

import com.example.LarryKitchenAcademy.dto.EnrollmentDto;
import com.example.LarryKitchenAcademy.dto.UpdateEnrollmentDto;
import com.example.LarryKitchenAcademy.entity.Enrollment;
import com.example.LarryKitchenAcademy.service.EnrollmentService;
import com.example.LarryKitchenAcademy.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/enrollment")
@CrossOrigin(origins = "*")
public class EnrollmentController {
    @Autowired
    private EnrollmentService service;

//    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/create")
    public ApiResponse<Object> addSingleEnrollment(@RequestBody EnrollmentDto enrollment){
        try{
            Enrollment response = service.saveEnrollment(enrollment);
            return ApiResponse.builder()
                    .message("Enrollment added successfully!")
                    .data(response)
                    .build();
        }catch (RuntimeException e){
            return ApiResponse.builder()
                    .message("Enrolment data creation failed!")
                    .data(e.getMessage())
                    .build();
        }
    }

    @PutMapping("/update")
    public ApiResponse<Object> updateEnrollmentStatus(@RequestBody UpdateEnrollmentDto updateStatus){
        try{
            Enrollment newEnrollment = service.findEnrollmentByEnrollmentIdAndUserId(updateStatus.getEnrollmentId(), updateStatus.getUserId());
            newEnrollment.setEnrollmentStatus(updateStatus.getEnrollmentStatus());

            EnrollmentDto enrollmentDto = new EnrollmentDto(
                    newEnrollment.getEnrollmentId(),
                    newEnrollment.getTrainingId(),
                    newEnrollment.getUserId(),
                    newEnrollment.getEnrollmentStatus(),
                    newEnrollment.getEnrollmentDate()
            );

            Enrollment response = service.saveEnrollment(enrollmentDto);
            return ApiResponse.builder()
                    .message("Enrollment status updated successfully!")
                    .data(response)
                    .build();
        }catch (RuntimeException e){
            return  ApiResponse.builder()
                    .message("Enrollment status failed to update!")
                    .data(e.getMessage())
                    .build();
        }
    }
}
