package com.example.course_service.controller;

import com.example.course_service.Entity.CourseEntity;
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

class CourseControllerGetAllCoursesTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController controller;

    private List<CourseEntity> courses;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        CourseEntity c1 = new CourseEntity();
        c1.setCourseId(1L);
        c1.setCourseName("Math");
        c1.setCourseCode("101");
        c1.setDescription("Mathematics");
        courses = List.of(c1);
    }

    @Test
    void testGetAllCourses() {
        when(courseService.getAllCourses()).thenReturn(courses);

        ResponseEntity<List<CourseEntity>> response = controller.getAllCourses();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }
}
