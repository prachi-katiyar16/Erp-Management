package com.example.course_service.service;

import com.example.course_service.Entity.CourseEntity;
import com.example.course_service.Entity.EnrollmentEntity;
import com.example.course_service.repository.CourseRepository;
import com.example.course_service.repository.EnrollmentRepository;
import com.example.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private CourseService courseService;

    private CourseEntity mockCourse;
    private EnrollmentEntity mockEnrollment;
    private Long mockCourseId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


        mockCourse = new CourseEntity(
                mockCourseId,
                "Microservices",
                "MS101",
                "Backend development course"
        );
        mockCourse.setEnrollments(new ArrayList<>());


        mockEnrollment = new EnrollmentEntity(
                100L,
                "S005",
                LocalDate.now().toString(),
                mockCourse
        );
    }

    @Test
    void getAllCourses_ShouldReturnAllCourses() {

        List<CourseEntity> courseList = Arrays.asList(mockCourse);
        when(courseRepository.findAll()).thenReturn(courseList);


        List<CourseEntity> result = courseService.getAllCourses();


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Microservices", result.get(0).getCourseName());


        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void getCourseById_WithValidId_ShouldReturnCourse() {

        when(courseRepository.findById(mockCourseId)).thenReturn(Optional.of(mockCourse));


        CourseEntity result = courseService.getCourseById(mockCourseId);


        assertNotNull(result);
        assertEquals(mockCourseId, result.getCourseId());

        verify(courseRepository, times(1)).findById(mockCourseId);
    }

    @Test
    void getCourseById_WithInvalidId_ShouldThrowException() {
        when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            courseService.getCourseById(99L);
        });

        assertEquals("Course not found with id: 99", exception.getMessage());

        verify(courseRepository, times(1)).findById(99L);
    }

    @Test
    void getEnrollmentsByCourse_ShouldReturnEnrollmentsList() {
        List<EnrollmentEntity> enrollments = Arrays.asList(mockEnrollment);
        when(enrollmentRepository.findByCourse_CourseId(mockCourseId)).thenReturn(enrollments);

        List<EnrollmentEntity> result = courseService.getEnrollmentsByCourse(mockCourseId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("S005", result.get(0).getStudentId());

        verify(enrollmentRepository, times(1)).findByCourse_CourseId(mockCourseId);
    }

    @Test
    void createCourse_ShouldSaveAndReturnCourse() {
        CourseEntity newCourse = new CourseEntity(null, "New", "N01", "Desc");
        CourseEntity savedCourse = new CourseEntity(2L, "New", "N01", "Desc");
        when(courseRepository.save(newCourse)).thenReturn(savedCourse);

        CourseEntity result = courseService.createCourse(newCourse);

        assertNotNull(result);
        assertEquals(2L, result.getCourseId());

        verify(courseRepository, times(1)).save(newCourse);
    }

    @Test
    void enrollStudent_ShouldSetDateAndCourseAndSaveEnrollment() {
        EnrollmentEntity newEnrollment = new EnrollmentEntity();
        newEnrollment.setStudentId("S007");

        when(courseRepository.findById(mockCourseId)).thenReturn(Optional.of(mockCourse));
        when(enrollmentRepository.save(any(EnrollmentEntity.class))).thenAnswer(invocation -> {
            EnrollmentEntity saved = invocation.getArgument(0);
            saved.setId(102L);
            return saved;
        });

        EnrollmentEntity result = courseService.enrollStudent(mockCourseId, newEnrollment);

        assertNotNull(result);
        assertEquals(102L, result.getId());
        assertEquals(mockCourse, result.getCourse());
        assertNotNull(result.getEnrollmentDate());
        assertEquals(LocalDate.now().toString(), result.getEnrollmentDate());

        verify(courseRepository, times(1)).findById(mockCourseId);
        verify(enrollmentRepository, times(1)).save(newEnrollment);
    }


    @Test
    void deleteCourse_WithValidId_ShouldCallDelete() {
        when(courseRepository.findById(mockCourseId)).thenReturn(Optional.of(mockCourse));
        doNothing().when(courseRepository).delete(any(CourseEntity.class));

        courseService.deleteCourse(mockCourseId);

        verify(courseRepository, times(1)).findById(mockCourseId);
        verify(courseRepository, times(1)).delete(mockCourse);
    }

    @Test
    void deleteCourse_WithInvalidId_ShouldThrowException() {
        when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            courseService.deleteCourse(99L);
        });

        verify(courseRepository, times(1)).findById(99L);
        verify(courseRepository, times(0)).delete(any(CourseEntity.class));
    }
}