package com.smartcampus.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String courseCode;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private Integer semester;

    @Column(nullable = false)
    private Integer credits;

    private String facultyId;
}