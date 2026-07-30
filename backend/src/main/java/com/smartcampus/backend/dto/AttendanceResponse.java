package com.smartcampus.backend.dto;

import com.smartcampus.backend.entity.AttendanceStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceResponse {

    private Long id;

    private Long studentId;

    private String studentName;

    private Long courseId;

    private String courseName;

    private LocalDate attendanceDate;

    private AttendanceStatus status;

}