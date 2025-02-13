package com.example.LarryKitchenAcademy.dto;

import com.example.LarryKitchenAcademy.utils.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class EnrollmentDto {
    private int enrollmentId;
    private int trainingId;
    private int userId;
    private EnrollmentStatus enrollmentStatus;
    private Date enrollmentDate;
}
