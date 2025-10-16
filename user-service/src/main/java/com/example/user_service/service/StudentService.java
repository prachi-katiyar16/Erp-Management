package com.example.user_service.service;

import com.example.user_service.entity.StudentEntity;
import com.example.exception.StudentNotFoundException;
import com.example.user_service.entity.StudentProfile;
import com.example.user_service.repository.StudentEntityRepository;
import com.example.user_service.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentProfileRepository profileRepository;

    @Autowired
    private StudentEntityRepository entityRepository;

    public StudentEntity createStudent(StudentEntity entity, StudentProfile profile) {
        profile.setStudent(entity);
        entity.setProfile(profile);
        return entityRepository.save(entity);
    }


    public StudentEntity updateStudent(String id, StudentEntity updated, StudentProfile profile) {
        return entityRepository.findById(id)
                .map(entity -> {
                    entity.setFullName(updated.getFullName());
                    entity.setBranch(updated.getBranch());
                    entity.setEnrollmentStatus(updated.getEnrollmentStatus());
                    if(profile!=null){
                        StudentProfile existingProfile =entity.getProfile();
                        if(existingProfile == null){
                            profile.setStudent(entity);
                            entity.setProfile(profile);
                        }else{
                            existingProfile.setPhoneNumber(profile.getPhoneNumber());
                            existingProfile.setAddress(profile.getAddress());
                            existingProfile.setDateOfBirth(profile.getDateOfBirth());
                        }
                    }
                    return entityRepository.save(entity);
                })
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }


    public void deleteStudent(String id) {
        entityRepository.deleteById(id);
    }


    public List<StudentEntity> getAllStudents() {
        return entityRepository.findAll();
    }

    public StudentEntity getStudentById(String id) {
        return entityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }
}
