package com.smartcampus.backend.controller;

import com.smartcampus.backend.common.ApiResponse;
import com.smartcampus.backend.dto.StudentRequest;
import com.smartcampus.backend.dto.StudentResponse;
import com.smartcampus.backend.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImpl studentService;

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> createStudent(
            @Valid @RequestBody StudentRequest request) {

        StudentResponse student = studentService.createStudent(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<StudentResponse>builder()
                        .success(true)
                        .message("Student created successfully")
                        .data(student)
                        .build());
    }

     @GetMapping
     public ResponseEntity<ApiResponse<Page<StudentResponse>>> getAllStudents(

              @RequestParam(defaultValue = "0") int page,
              @RequestParam(defaultValue = "5") int size,
              @RequestParam(defaultValue = "id") String sortBy,
              @RequestParam(defaultValue = "asc") String direction) {

        Page<StudentResponse> students =
                studentService.getAllStudents(page, size, sortBy, direction);

        return ResponseEntity.ok(
                ApiResponse.<Page<StudentResponse>>builder()
                        .success(true)
                        .message("Students retrieved successfully")
                        .data(students)
                        .build()
        );
     }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentById(
            @PathVariable Long id) {

        StudentResponse student = studentService.getStudentById(id);

        return ResponseEntity.ok(
                ApiResponse.<StudentResponse>builder()
                        .success(true)
                        .message("Student retrieved successfully")
                        .data(student)
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request) {

        StudentResponse student = studentService.updateStudent(id, request);

        return ResponseEntity.ok(
                ApiResponse.<StudentResponse>builder()
                        .success(true)
                        .message("Student updated successfully")
                        .data(student)
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Student deleted successfully")
                        .data(null)
                        .build());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StudentResponse>>> searchStudents(
            @RequestParam String keyword) {

        List<StudentResponse> students = studentService.searchStudents(keyword);

        return ResponseEntity.ok(
                ApiResponse.<List<StudentResponse>>builder()
                        .success(true)
                        .message("Search completed successfully")
                        .data(students)
                        .build());
    }
}