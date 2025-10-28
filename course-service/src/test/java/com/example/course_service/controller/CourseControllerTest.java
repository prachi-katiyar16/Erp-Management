package com.example.course_service.controller;

import com.example.course_service.Entity.CourseEntity;
import com.example.course_service.Entity.EnrollmentEntity;
import com.example.course_service.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private CourseEntity mockCourse;
    private EnrollmentEntity mockEnrollment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockCourse = new CourseEntity(
                1L,
                "Microservices",
                "MS101",
                "Backend development course"
        );
        mockCourse.setEnrollments(Arrays.asList());

        mockEnrollment = new EnrollmentEntity(
                100L,
                "S005",
                "2025-10-17",
                mockCourse
        );
    }

    @Test
    void createCourse_AsAdmin_ShouldReturnCreatedCourse() {

        CourseEntity courseToCreate = new CourseEntity(null, "New Course", "NC01", "New description");
        CourseEntity createdCourse = new CourseEntity(2L, "New Course", "NC01", "New description");

        when(courseService.createCourse(any(CourseEntity.class))).thenReturn(createdCourse);


        ResponseEntity<CourseEntity> response = courseController.createCourse(courseToCreate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2L, response.getBody().getCourseId());

        verify(courseService, times(1)).createCourse(courseToCreate);
    }

    @Test
    void enrollStudent_AsAdmin_ShouldReturnCreatedEnrollment() {
        Long courseId = 1L;
        EnrollmentEntity newEnrollment = new EnrollmentEntity(null, "S006", "2025-10-18", null);
        EnrollmentEntity savedEnrollment = new EnrollmentEntity(101L, "S006", "2025-10-18", mockCourse);

        when(courseService.enrollStudent(eq(courseId), any(EnrollmentEntity.class))).thenReturn(savedEnrollment);

        ResponseEntity<EnrollmentEntity> response = courseController.enrollStudent(courseId, newEnrollment);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(101L, response.getBody().getId());
        assertEquals("S006", response.getBody().getStudentId());

        verify(courseService, times(1)).enrollStudent(courseId, newEnrollment);
    }

    @Test
    void deleteCourse_AsAdmin_ShouldReturnNoContent() {
        Long courseId = 1L;
        doNothing().when(courseService).deleteCourse(courseId);

        ResponseEntity<Void> response = courseController.deleteCourse(courseId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(courseService, times(1)).deleteCourse(courseId);
    }


    @Test
    void getAllCourses_AsAuthorizedUser_ShouldReturnAllCourses() {

        List<CourseEntity> courses = Arrays.asList(mockCourse);
        when(courseService.getAllCourses()).thenReturn(courses);

        ResponseEntity<List<CourseEntity>> response = courseController.getAllCourses();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("MS101", response.getBody().get(0).getCourseCode());

        verify(courseService, times(1)).getAllCourses();
    }

    @Test
    void getCourseById_AsAuthorizedUser_ShouldReturnCourse() {
        Long courseId = 1L;
        when(courseService.getCourseById(courseId)).thenReturn(mockCourse);

        ResponseEntity<CourseEntity> response = courseController.getCourseById(courseId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Microservices", response.getBody().getCourseName());

        verify(courseService, times(1)).getCourseById(courseId);
    }

    @Test
    void getEnrollmentsByCourse_Unsecured_ShouldReturnEnrollmentsList() {
        Long courseId = 1L;
        List<EnrollmentEntity> enrollments = Arrays.asList(mockEnrollment);
        when(courseService.getEnrollmentsByCourse(courseId)).thenReturn(enrollments);

        ResponseEntity<List<EnrollmentEntity>> response = courseController.getEnrollmentsByCourse(courseId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("S005", response.getBody().get(0).getStudentId());

        verify(courseService, times(1)).getEnrollmentsByCourse(courseId);
    }
}