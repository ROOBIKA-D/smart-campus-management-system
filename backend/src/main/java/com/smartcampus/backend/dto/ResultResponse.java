package com.smartcampus.backend.dto;

import com.smartcampus.backend.entity.Grade;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse {

    private Long id;

    private Long studentId;

    private String studentName;

    private Long courseId;

    private String courseName;

    private Integer internalMarks;

    private Integer externalMarks;

    private Integer totalMarks;

    private Grade grade;

    private Boolean pass;

}