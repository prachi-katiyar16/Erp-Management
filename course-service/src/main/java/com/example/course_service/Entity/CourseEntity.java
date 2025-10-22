package com.example.course_service.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseName;
    private String courseCode;
    private String description;

    @OneToMany(mappedBy = "course",cascade= CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<EnrollmentEntity> enrollments=new ArrayList<>();

    public CourseEntity(Long courseId, String courseName, String courseCode, String description) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.description = description;
    }
}