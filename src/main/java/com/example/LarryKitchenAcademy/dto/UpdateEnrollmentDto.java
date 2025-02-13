package com.example.LarryKitchenAcademy.dto;

import com.example.LarryKitchenAcademy.utils.EnrollmentStatus;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateEnrollmentDto {
    private int enrollmentId;
    private int userId;
    private EnrollmentStatus enrollmentStatus;
}
