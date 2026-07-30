package com.smartcampus.backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponse {

    private Long id;

    private String title;

    private String content;

    private String audience;

    private String postedBy;

    private LocalDateTime postedOn;
}