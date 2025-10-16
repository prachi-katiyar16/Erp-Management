package com.example.user_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name="Student_Entity")
public class StudentEntity {

    @Id
    private String studentId;
    private String fullName;
    private String branch;
    private String enrollmentStatus;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private StudentProfile profile;
}
