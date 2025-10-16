package com.example.course_service.controller;

import com.example.course_service.Entity.CourseEntity;
import com.example.course_service.Entity.EnrollmentEntity;
import com.example.course_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<CourseEntity> createCourse(@RequestBody CourseEntity course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{courseId}/enrollments")
    public ResponseEntity<EnrollmentEntity> enrollStudent(@PathVariable Long courseId,
                                                          @RequestBody EnrollmentEntity enrollment) {
        EnrollmentEntity saved = courseService.enrollStudent(courseId, enrollment);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/courseId/{courseId}")
    public ResponseEntity<List<EnrollmentEntity>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getEnrollmentsByCourse(courseId));
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<CourseEntity>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CourseEntity> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
