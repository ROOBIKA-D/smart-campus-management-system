package com.smartcampus.backend.controller;

import com.smartcampus.backend.common.ApiResponse;
import com.smartcampus.backend.dto.FacultyRequest;
import com.smartcampus.backend.dto.FacultyResponse;
import com.smartcampus.backend.service.FacultyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @PostMapping
    public ResponseEntity<ApiResponse<FacultyResponse>> createFaculty(
            @Valid @RequestBody FacultyRequest request) {

        FacultyResponse faculty = facultyService.createFaculty(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<FacultyResponse>builder()
                        .success(true)
                        .message("Faculty created successfully")
                        .data(faculty)
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FacultyResponse>>> getAllFaculty() {

        List<FacultyResponse> faculty = facultyService.getAllFaculty();

        return ResponseEntity.ok(
                ApiResponse.<List<FacultyResponse>>builder()
                        .success(true)
                        .message("Faculty retrieved successfully")
                        .data(faculty)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FacultyResponse>> getFacultyById(
            @PathVariable Long id) {

        FacultyResponse faculty = facultyService.getFacultyById(id);

        return ResponseEntity.ok(
                ApiResponse.<FacultyResponse>builder()
                        .success(true)
                        .message("Faculty retrieved successfully")
                        .data(faculty)
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FacultyResponse>> updateFaculty(
            @PathVariable Long id,
            @Valid @RequestBody FacultyRequest request) {

        FacultyResponse faculty = facultyService.updateFaculty(id, request);

        return ResponseEntity.ok(
                ApiResponse.<FacultyResponse>builder()
                        .success(true)
                        .message("Faculty updated successfully")
                        .data(faculty)
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteFaculty(
            @PathVariable Long id) {

        facultyService.deleteFaculty(id);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Faculty deleted successfully")
                        .data(null)
                        .build());
    }
}