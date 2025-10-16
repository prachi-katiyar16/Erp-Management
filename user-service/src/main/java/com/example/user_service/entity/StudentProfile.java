package com.example.user_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jmx.export.annotation.ManagedNotifications;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="student_profile")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    private String phoneNumber;
    private String address;
    private String dateOfBirth;

    @OneToOne
    @JoinColumn(name="student_Id", referencedColumnName = "studentId")
    @JsonBackReference
    private StudentEntity student;
}
