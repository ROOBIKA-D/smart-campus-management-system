package com.smartcampus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private Long id;
    private String rollNumber;
    private String name;
    private String email;
    private String department;
    private Integer year;
    private String phone;
}