package com.example.LarryKitchenAcademy.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RequestTrainingDto {
    private int userId;
    private String trainingName;
    private String trainingDesc;
    private int trainingCapacity;
    private String trainingClass;
    private Date trainingDate;
}
