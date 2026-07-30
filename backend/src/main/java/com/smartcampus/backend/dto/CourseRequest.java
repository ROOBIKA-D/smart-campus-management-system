package com.smartcampus.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {

    @NotBlank
    private String courseCode;

    @NotBlank
    private String courseName;

    @NotBlank
    private String department;

    @NotNull
    private Integer semester;

    @NotNull
    private Integer credits;

    private String facultyId;
}