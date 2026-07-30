package com.smartcampus.backend.dto;

import com.smartcampus.backend.entity.AttendanceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRequest {

    @NotNull
    private Long studentId;

    @NotNull
    private Long courseId;

    @NotNull
    private LocalDate attendanceDate;

    @NotNull
    private AttendanceStatus status;

}