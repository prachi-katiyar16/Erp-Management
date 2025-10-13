package com.example.course_service.repository;

import com.example.course_service.Entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {
    List<EnrollmentEntity> findByStudentId(String studentId);
    List<EnrollmentEntity> findByCourseId(Long courseId);
}