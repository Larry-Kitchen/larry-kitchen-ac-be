package com.example.LarryKitchenAcademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LarryKitchenAcademy.entity.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{
    
}
