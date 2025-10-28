package com.example.user_service.service;

import com.example.exception.StudentNotFoundException;
import com.example.user_service.entity.StudentEntity;
import com.example.user_service.entity.StudentProfile;
import com.example.user_service.repository.StudentEntityRepository;
import com.example.user_service.repository.StudentProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentEntityRepository entityRepository;

    @Mock
    private StudentProfileRepository profileRepository;

    @InjectMocks
    private StudentService studentService;

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
        profile.setStudent(student);
    }

    @Test
    void testCreateStudent() {
        when(entityRepository.save(student)).thenReturn(student);

        StudentEntity created = studentService.createStudent(student, profile);

        assertEquals("100A", created.getStudentId());
        assertEquals("Aditi Sharma", created.getFullName());
        assertEquals(profile, created.getProfile());
        verify(entityRepository, times(1)).save(student);
    }

    @Test
    void testUpdateStudent_Success() {
        StudentEntity updatedStudent = new StudentEntity();
        updatedStudent.setFullName("Aditi S.");
        updatedStudent.setBranch("Computer Science");
        updatedStudent.setEnrollmentStatus("Active");

        StudentProfile updatedProfile = new StudentProfile();
        updatedProfile.setPhoneNumber("9999999999");
        updatedProfile.setAddress("Delhi");

        when(entityRepository.findById("100A")).thenReturn(Optional.of(student));
        when(entityRepository.save(any(StudentEntity.class))).thenReturn(student);

        StudentEntity result = studentService.updateStudent("100A", updatedStudent, updatedProfile);

        assertEquals("Aditi S.", result.getFullName());
        assertEquals("Computer Science", result.getBranch());
        assertEquals("9999999999", result.getProfile().getPhoneNumber());
        verify(entityRepository, times(1)).findById("100A");
        verify(entityRepository, times(1)).save(student);
    }

    @Test
    void testUpdateStudent_NotFound() {
        when(entityRepository.findById("100B")).thenReturn(Optional.empty());

        StudentEntity updatedStudent = new StudentEntity();
        StudentProfile updatedProfile = new StudentProfile();

        assertThrows(StudentNotFoundException.class, () ->
                studentService.updateStudent("100B", updatedStudent, updatedProfile));
    }

    @Test
    void testDeleteStudent() {
        doNothing().when(entityRepository).deleteById("100A");

        studentService.deleteStudent("100A");

        verify(entityRepository, times(1)).deleteById("100A");
    }

    @Test
    void testGetAllStudents() {
        when(entityRepository.findAll()).thenReturn(Arrays.asList(student));

        List<StudentEntity> students = studentService.getAllStudents();

        assertEquals(1, students.size());
        assertEquals("100A", students.get(0).getStudentId());
        verify(entityRepository, times(1)).findAll();
    }

    @Test
    void testGetStudentById_Success() {
        when(entityRepository.findById("100A")).thenReturn(Optional.of(student));

        StudentEntity result = studentService.getStudentById("100A");

        assertEquals("Aditi Sharma", result.getFullName());
        verify(entityRepository, times(1)).findById("100A");
    }

    @Test
    void testGetStudentById_NotFound() {
        when(entityRepository.findById("100B")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> studentService.getStudentById("100B"));
    }
}
