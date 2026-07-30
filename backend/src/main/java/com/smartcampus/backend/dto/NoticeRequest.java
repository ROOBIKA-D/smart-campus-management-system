package com.smartcampus.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String audience;

    @NotBlank
    private String postedBy;
}