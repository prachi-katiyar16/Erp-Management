package com.example.user_service.controller;


import com.example.user_service.entity.StudentEntity;
import com.example.user_service.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService service;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudent(){
        return ResponseEntity.ok(service.getAllStudents());
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Long id) {
        StudentEntity student = service.getStudentById(id);
        return ResponseEntity.ok(student);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity entity){
        return ResponseEntity.ok(service.createStudent(entity));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable Long id, @RequestBody StudentEntity entity) {
        return ResponseEntity.ok(service.updateStudent(id, entity));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDetail(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.ok("Detail deleted successfully");
    }
}

























