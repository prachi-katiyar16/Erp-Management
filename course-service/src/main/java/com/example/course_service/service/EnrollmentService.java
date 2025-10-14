package com.example.course_service.service;

import com.example.course_service.Entity.CourseEntity;
import com.example.course_service.Entity.EnrollmentEntity;
import com.example.course_service.dto.UserDto;
import com.example.course_service.exception.ResourceNotFoundException;
import com.example.course_service.repository.CourseRepository;
import com.example.course_service.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;




    public EnrollmentEntity enrollStudent( Long courseId, String studentId){
        CourseEntity course =courseRepository.findById(courseId)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found with id: " + courseId));


        EnrollmentEntity enrollment = new EnrollmentEntity();
        enrollment.setCourseId(courseId);
        enrollment.setStudentId(studentId);
        enrollment.setEnrollmentDate(LocalDate.now().toString());

        return enrollmentRepository.save(enrollment);
    }

    public List<EnrollmentEntity> getEnrollmentsByStudent(String studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<EnrollmentEntity> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public void deleteEnrollment(Long id) {
        EnrollmentEntity enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
        enrollmentRepository.delete(enrollment);
    }



}







