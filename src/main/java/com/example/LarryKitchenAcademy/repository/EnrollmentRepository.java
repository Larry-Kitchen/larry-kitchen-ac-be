package com.example.LarryKitchenAcademy.repository;
import com.example.LarryKitchenAcademy.dto.EnrollmentDto;
import com.example.LarryKitchenAcademy.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer>{
    Optional<Enrollment> findEnrollmentByEnrollmentId(int enrollmentId);
    Enrollment findEnrollmentByEnrollmentIdAndUserId(int enrollmentId, int user_id);
}
