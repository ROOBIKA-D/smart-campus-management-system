package com.smartcampus.backend.controller;

import com.smartcampus.backend.common.ApiResponse;
import com.smartcampus.backend.dto.*;
import com.smartcampus.backend.service.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<ApiResponse<ResultResponse>> publishResult(
            @Valid @RequestBody ResultRequest request) {

        ResultResponse result = resultService.publishResult(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ResultResponse>builder()
                        .success(true)
                        .message("Result published successfully")
                        .data(result)
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ResultResponse>>> getAllResults() {

        return ResponseEntity.ok(
                ApiResponse.<List<ResultResponse>>builder()
                        .success(true)
                        .message("Results retrieved successfully")
                        .data(resultService.getAllResults())
                        .build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<ResultResponse>>> getStudentResults(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                ApiResponse.<List<ResultResponse>>builder()
                        .success(true)
                        .message("Student results retrieved successfully")
                        .data(resultService.getStudentResults(studentId))
                        .build());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<List<ResultResponse>>> getCourseResults(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(
                ApiResponse.<List<ResultResponse>>builder()
                        .success(true)
                        .message("Course results retrieved successfully")
                        .data(resultService.getCourseResults(courseId))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteResult(
            @PathVariable Long id) {

        resultService.deleteResult(id);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Result deleted successfully")
                        .data(null)
                        .build());
    }
}