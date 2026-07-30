package com.smartcampus.backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacultyResponse {

    private Long id;

    private String facultyId;

    private String name;

    private String email;

    private String department;

    private String designation;

    private String phone;
}