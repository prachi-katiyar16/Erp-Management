package com.example.user_service.controller;

import com.example.user_service.entity.StudentEntity;
import com.example.user_service.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProfileControllerGetAllStudentsTest {

    @Mock
    private StudentService service;

    @InjectMocks
    private ProfileController controller;

    private List<StudentEntity> students;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        StudentEntity student1 = new StudentEntity();
        student1.setStudentId("100A");
        student1.setFullName("Aditi Sharma");

        StudentEntity student2 = new StudentEntity();
        student2.setStudentId("100B");
        student2.setFullName("Rahul Kumar");

        students = List.of(student1, student2);
    }

    @Test
    void testGetAllStudents() {
        when(service.getAllStudents()).thenReturn(students);

        ResponseEntity<List<StudentEntity>> response = controller.getAllStudent();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }
}
