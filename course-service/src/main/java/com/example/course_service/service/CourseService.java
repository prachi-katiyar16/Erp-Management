package com.example.course_service.service;

import com.example.course_service.Entity.CourseEntity;
import com.example.course_service.Entity.EnrollmentEntity;
import com.example.course_service.repository.EnrollmentRepository;
import com.example.exception.ResourceNotFoundException;
import com.example.course_service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;


    public List<CourseEntity> getAllCourses(){

        return courseRepository.findAll();
    }

    public CourseEntity getCourseById(Long id){
       return courseRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Course not found with id: "+id));
    }

    public List<EnrollmentEntity> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourse_CourseId(courseId);
    }

    public CourseEntity createCourse(CourseEntity entity) {
        return courseRepository.save(entity);
    }

    public EnrollmentEntity enrollStudent(Long courseId, EnrollmentEntity enrollment){
        CourseEntity course =getCourseById(courseId);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now().toString());

        return enrollmentRepository.save(enrollment);
    }


    public void deleteCourse(Long id){
       CourseEntity course=getCourseById(id);
       courseRepository.delete(course);
    }



}






















