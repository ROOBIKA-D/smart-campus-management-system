package com.smartcampus.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultRequest {

    private Long studentId;

    private Long courseId;

    private Integer internalMarks;

    private Integer externalMarks;

}