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

class ProfileControllerUpdateStudentTest {

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
        profile.setPhoneNumber("9876543210");
        student.setProfile(profile);
    }

    @Test
    void testUpdateStudent() {
        when(service.updateStudent("100A", student, profile)).thenReturn(student);

        ResponseEntity<StudentEntity> response = controller.updateStudent("100A", student, profile);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(student, response.getBody());
    }
}
