package com.example.course_service.controller;

import com.example.course_service.Entity.EnrollmentEntity;
import com.example.course_service.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CourseControllerGetEnrollmentsByCourseTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController controller;

    private List<EnrollmentEntity> enrollments;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        EnrollmentEntity e1 = new EnrollmentEntity();
        e1.setId(1L);
        e1.setStudentId("S100");
        enrollments = List.of(e1);
    }

    @Test
    void testGetEnrollmentsByCourse() {
        when(courseService.getEnrollmentsByCourse(1L)).thenReturn(enrollments);

        ResponseEntity<List<EnrollmentEntity>> response = controller.getEnrollmentsByCourse(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }
}
