package com.example.course_service.controller;

import com.example.course_service.Entity.EnrollmentEntity;
import com.example.course_service.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CourseControllerEnrollStudentTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController controller;

    private EnrollmentEntity enrollment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enrollment = new EnrollmentEntity();
        enrollment.setId(1L);
        enrollment.setStudentId("S100");
    }

    @Test
    void testEnrollStudent() {
        when(courseService.enrollStudent(1L, enrollment)).thenReturn(enrollment);

        ResponseEntity<EnrollmentEntity> response = controller.enrollStudent(1L, enrollment);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(enrollment, response.getBody());
    }
}
