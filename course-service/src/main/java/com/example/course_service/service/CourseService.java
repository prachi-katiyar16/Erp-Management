package com.example.course_service.service;

import com.example.course_service.Entity.CourseEntity;
import com.example.course_service.dto.UserDto;
import com.example.course_service.exception.ResourceNotFoundException;
import com.example.course_service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;



    public List<CourseEntity> getAllCourses(){
        return courseRepository.findAll();
    }

    public CourseEntity getCourseById(Long id){
       return courseRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Course not found with id: "+id));
    }

    public CourseEntity createCourse(CourseEntity entity) {
        return courseRepository.save(entity);
    }

    public void deleteCourse(Long id){
       CourseEntity course=getCourseById(id);
       courseRepository.delete(course);
    }


}






















