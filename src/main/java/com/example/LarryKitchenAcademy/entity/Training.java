package com.example.LarryKitchenAcademy.entity;

import com.example.LarryKitchenAcademy.utils.TrainingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "training")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id")
    private int trainingId;

    @Column(name = "training_name", nullable = false)
    private String trainingName;

    @Column(name = "training_desc", length = 1000)
    private String trainingDesc;

    @Column(name = "training_capacity", nullable = false)
    private int trainingCapacity;

    @Column(name = "training_classroom", nullable = false)
    private String trainingClass;

    @Enumerated(EnumType.STRING)
    @Column(name = "training_status", nullable = false)
    private TrainingStatus trainingStatus;

    @Column(name = "training_teacher_id", nullable = false)
    private int trainingTeacherId;

    @Column(name = "training_date")
    private Date trainingDate;

    @Column(name = "training_create_date")
    private Date trainingCreateDate;

}
