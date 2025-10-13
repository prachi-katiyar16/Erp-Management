package com.example.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name="Student_Profiles")
public class StudentEntity {

    @Id
    private Long userId;
    private String studentId;
    private String fullName;
    private String branch;
    private String enrollmentStatus;
}
