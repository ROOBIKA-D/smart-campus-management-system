package com.smartcampus.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    private Integer internalMarks;

    private Integer externalMarks;

    private Integer totalMarks;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private Boolean pass;

}