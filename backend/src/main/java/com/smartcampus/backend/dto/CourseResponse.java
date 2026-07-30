package com.smartcampus.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {

    private Long id;
    private String courseCode;
    private String courseName;
    private String department;
    private Integer semester;
    private Integer credits;
    private String facultyId;

}