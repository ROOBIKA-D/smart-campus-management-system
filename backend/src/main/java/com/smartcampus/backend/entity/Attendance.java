package com.smartcampus.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="attendance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    private Student student;

    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    private Course course;

    private LocalDate attendanceDate;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;
}