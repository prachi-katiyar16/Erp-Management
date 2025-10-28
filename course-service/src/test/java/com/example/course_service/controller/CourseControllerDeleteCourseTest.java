package com.example.course_service.controller;

import com.example.course_service.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

class CourseControllerDeleteCourseTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteCourse() {
        doNothing().when(courseService).deleteCourse(1L);

        ResponseEntity<Void> response = controller.deleteCourse(1L);

        assertEquals(204, response.getStatusCodeValue());
    }
}
