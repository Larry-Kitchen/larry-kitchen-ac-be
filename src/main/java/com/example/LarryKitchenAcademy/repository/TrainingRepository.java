package com.example.LarryKitchenAcademy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.LarryKitchenAcademy.entity.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer>{
    
    @Query("SELECT t, u.username FROM Training t JOIN User u ON t.trainingTeacherId = u.id")
    List<Object[]> findAllTrainingWithTeacherName();
}
