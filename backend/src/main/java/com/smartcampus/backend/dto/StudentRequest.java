package com.smartcampus.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRequest {

    @NotBlank(message = "Roll Number is required")
    private String rollNumber;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Year is required")
    private Integer year;

    @NotBlank(message = "Phone Number is required")
    private String phone;
}