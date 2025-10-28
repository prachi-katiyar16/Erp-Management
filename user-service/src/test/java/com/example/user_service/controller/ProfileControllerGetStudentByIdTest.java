package com.example.user_service.controller;

import com.example.user_service.entity.StudentEntity;
import com.example.user_service.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProfileControllerGetStudentByIdTest {

    @Mock
    private StudentService service;

    @InjectMocks
    private ProfileController controller;

    private StudentEntity student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new StudentEntity();
        student.setStudentId("100A");
        student.setFullName("Aditi Sharma");
    }

    @Test
    void testGetStudentById() {
        when(service.getStudentById("100A")).thenReturn(student);

        ResponseEntity<StudentEntity> response = controller.getStudentById("100A");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Aditi Sharma", response.getBody().getFullName());
    }
}
