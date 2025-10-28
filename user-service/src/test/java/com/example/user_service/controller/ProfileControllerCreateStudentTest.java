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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProfileControllerCreateStudentTest {

    @Mock
    private StudentService service;

    @InjectMocks
    private ProfileController controller;

    private StudentEntity student;
    private StudentProfile profile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new StudentEntity();
        student.setStudentId("100A");
        student.setFullName("Aditi Sharma");

        profile = new StudentProfile();
        profile.setProfileId(1L);
        profile.setPhoneNumber("9876543210");

        student.setProfile(profile);
    }

    @Test
    void testCreateStudent() {
        when(service.createStudent(student, profile)).thenReturn(student);

        ResponseEntity<StudentEntity> response = controller.createStudent(student);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(student, response.getBody());
    }
}
