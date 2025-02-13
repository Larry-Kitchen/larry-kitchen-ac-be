package com.example.LarryKitchenAcademy.controller;

import com.example.LarryKitchenAcademy.dto.EnrollmentDto;
import com.example.LarryKitchenAcademy.entity.Enrollment;
import com.example.LarryKitchenAcademy.service.EnrollmentService;
import com.example.LarryKitchenAcademy.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/enrollment")
@CrossOrigin(origins = "*")
public class EnrollmentController {
    @Autowired
    private EnrollmentService service;

//    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/create")
    public ApiResponse<Object> addSingleEnrollment(@RequestBody EnrollmentDto enrollment){
        try{
            return ApiResponse.builder()
                    .message("Enrollment added successfully!")
                    .data(service.saveEnrollment(enrollment))
                    .build();
        }catch (RuntimeException e){
            return ApiResponse.builder()
                    .message("Enrolment data creation failed!")
                    .data(e.getMessage())
                    .build();
        }
    }
}
