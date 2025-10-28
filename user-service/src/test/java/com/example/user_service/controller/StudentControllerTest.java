package com.example.user_service.controller;

import com.example.user_service.entity.StudentEntity;
import com.example.user_service.entity.StudentProfile;
import com.example.user_service.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentControllerTest {
    @Mock
    private StudentService studentService;

    @InjectMocks
    private ProfileController profileController;

    private StudentEntity student;
    private StudentProfile profile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample profile
        profile = new StudentProfile();
        profile.setProfileId(1L);
        profile.setPhoneNumber("9876543210");
        profile.setAddress("Hyderabad, India");

        // Sample student
        student = new StudentEntity();
        student.setStudentId("100A");
        student.setFullName("Aditi Sharma");
        student.setBranch("Electronics");
        student.setEnrollmentStatus("Active");
        student.setProfile(profile);
    }

    @Test
    void testGetAllStudent() {
        List<StudentEntity> students = Arrays.asList(student);
        when(studentService.getAllStudents()).thenReturn(students);

        ResponseEntity<List<StudentEntity>> response = profileController.getAllStudent();

        assertEquals(1, response.getBody().size());
        assertEquals("100A", response.getBody().get(0).getStudentId());
        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void testGetStudentById() {
        when(studentService.getStudentById("100A")).thenReturn(student);

        ResponseEntity<StudentEntity> response = profileController.getStudentById("100A");

        assertEquals("Aditi Sharma", response.getBody().getFullName());
        verify(studentService, times(1)).getStudentById("100A");
    }

    @Test
    void testCreateStudent() {
        when(studentService.createStudent(student, profile)).thenReturn(student);

        ResponseEntity<StudentEntity> response = profileController.createStudent(student);

        assertEquals("Electronics", response.getBody().getBranch());
        verify(studentService, times(1)).createStudent(student, profile);
    }

    @Test
    void testUpdateStudent() {
        when(studentService.updateStudent("100A", student, profile)).thenReturn(student);

        ResponseEntity<StudentEntity> response = profileController.updateStudent("100A", student, profile);

        assertEquals("Active", response.getBody().getEnrollmentStatus());
        verify(studentService, times(1)).updateStudent("100A", student, profile);
    }

    @Test
    void testDeleteDetail() {
        doNothing().when(studentService).deleteStudent("100A");

        ResponseEntity<String> response = profileController.deleteDetail("100A");

        assertEquals("Detail deleted successfully", response.getBody());
        verify(studentService, times(1)).deleteStudent("100A");
    }
}