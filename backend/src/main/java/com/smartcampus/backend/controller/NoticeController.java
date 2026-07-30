package com.smartcampus.backend.controller;

import com.smartcampus.backend.common.ApiResponse;
import com.smartcampus.backend.dto.*;
import com.smartcampus.backend.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<ApiResponse<NoticeResponse>> createNotice(
            @Valid @RequestBody NoticeRequest request) {

        NoticeResponse notice = noticeService.createNotice(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<NoticeResponse>builder()
                        .success(true)
                        .message("Notice created successfully")
                        .data(notice)
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<NoticeResponse>>> getAllNotices() {

        return ResponseEntity.ok(
                ApiResponse.<List<NoticeResponse>>builder()
                        .success(true)
                        .message("Notices retrieved successfully")
                        .data(noticeService.getAllNotices())
                        .build());
    }

    @GetMapping("/audience/{audience}")
    public ResponseEntity<ApiResponse<List<NoticeResponse>>> getAudienceNotices(
            @PathVariable String audience) {

        return ResponseEntity.ok(
                ApiResponse.<List<NoticeResponse>>builder()
                        .success(true)
                        .message("Audience notices retrieved successfully")
                        .data(noticeService.getAudienceNotices(audience))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteNotice(
            @PathVariable Long id) {

        noticeService.deleteNotice(id);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Notice deleted successfully")
                        .data(null)
                        .build());
    }
}