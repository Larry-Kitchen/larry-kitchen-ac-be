package com.example.LarryKitchenAcademy.dto;

import java.util.Date;

import com.example.LarryKitchenAcademy.utils.TrainingStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DashboardDto {
    private int trainingId;
    private String trainingName;
    private String trainingDesc;
    private int trainingCapacity;
    private String trainingClass;
    private TrainingStatus trainingStatus;
    private int trainingTeacherId;
    private Date trainingDate;
    private String trainingTeacherName;
}
