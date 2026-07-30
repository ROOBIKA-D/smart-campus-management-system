package com.smartcampus.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultyRequest {

    @NotBlank
    private String facultyId;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String department;

    @NotBlank
    private String designation;

    @NotBlank
    private String phone;
}