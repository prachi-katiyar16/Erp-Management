package com.example.user_service.service;

import com.example.user_service.entity.StudentEntity;
import com.example.user_service.exception.StudentNotFoundException;
import com.example.user_service.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private StudentProfileRepository repository;

    public StudentEntity createStudent(StudentEntity entity) {
        return repository.save(entity);
    }


    public StudentEntity updateStudent(Long id, StudentEntity updated) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setFullName(updated.getFullName());
                    entity.setBranch(updated.getBranch());
                    entity.setEnrollmentStatus(updated.getEnrollmentStatus());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }


    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }


    public List<StudentEntity> getAllStudents() {
        return repository.findAll();
    }

    public StudentEntity getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }
}
