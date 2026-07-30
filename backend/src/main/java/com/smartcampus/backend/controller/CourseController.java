package com.smartcampus.backend.controller;

import com.smartcampus.backend.common.ApiResponse;
import com.smartcampus.backend.dto.CourseRequest;
import com.smartcampus.backend.dto.CourseResponse;
import com.smartcampus.backend.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(
            @Valid @RequestBody CourseRequest request) {

        CourseResponse course = courseService.createCourse(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<CourseResponse>builder()
                        .success(true)
                        .message("Course created successfully")
                        .data(course)
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourses() {

        return ResponseEntity.ok(
                ApiResponse.<List<CourseResponse>>builder()
                        .success(true)
                        .message("Courses retrieved successfully")
                        .data(courseService.getAllCourses())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> getCourseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<CourseResponse>builder()
                        .success(true)
                        .message("Course retrieved successfully")
                        .data(courseService.getCourseById(id))
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<CourseResponse>builder()
                        .success(true)
                        .message("Course updated successfully")
                        .data(courseService.updateCourse(id, request))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Course deleted successfully")
                        .data(null)
                        .build());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CourseResponse>>> searchCourse(
            @RequestParam String courseName) {

        return ResponseEntity.ok(
                ApiResponse.<List<CourseResponse>>builder()
                        .success(true)
                        .message("Search completed successfully")
                        .data(courseService.searchCourse(courseName))
                        .build());
    }
}