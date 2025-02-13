package com.example.LarryKitchenAcademy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LarryKitchenAcademy.entity.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer>{
    Optional<Training> findByTrainingTeacherId(int trainingTeacherIduserId);
}
