package com.example.user_service.repository;

import com.example.user_service.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentEntityRepository extends JpaRepository<StudentEntity, String> {
}
