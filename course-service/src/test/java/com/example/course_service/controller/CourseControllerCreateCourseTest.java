package com.example.course_service.controller;

import com.example.course_service.Entity.CourseEntity;
import com.example.course_service.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CourseControllerCreateCourseTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController controller;

    private CourseEntity course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        course = new CourseEntity();
        course.setCourseId(1L);
        course.setCourseName("Math");
        course.setCourseCode("101");
        course.setDescription("Mathematics");
    }

    @Test
    void testCreateCourse() {
        when(courseService.createCourse(course)).thenReturn(course);

        ResponseEntity<CourseEntity> response = controller.createCourse(course);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(course, response.getBody());
    }
}
