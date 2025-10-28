package com.example.user_service.controller;

import com.example.user_service.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

class ProfileControllerDeleteStudentTest {

    @Mock
    private StudentService service;

    @InjectMocks
    private ProfileController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteStudent() {
        doNothing().when(service).deleteStudent("100A");

        ResponseEntity<String> response = controller.deleteDetail("100A");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Detail deleted successfully", response.getBody());
    }
}
