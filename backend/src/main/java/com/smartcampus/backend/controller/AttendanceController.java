package com.smartcampus.backend.controller;

import com.smartcampus.backend.common.ApiResponse;
import com.smartcampus.backend.dto.AttendanceRequest;
import com.smartcampus.backend.dto.AttendanceResponse;
import com.smartcampus.backend.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<ApiResponse<AttendanceResponse>> markAttendance(
            @Valid @RequestBody AttendanceRequest request) {

        AttendanceResponse attendance = attendanceService.markAttendance(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<AttendanceResponse>builder()
                        .success(true)
                        .message("Attendance marked successfully")
                        .data(attendance)
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAllAttendance() {

        return ResponseEntity.ok(
                ApiResponse.<List<AttendanceResponse>>builder()
                        .success(true)
                        .message("Attendance retrieved successfully")
                        .data(attendanceService.getAllAttendance())
                        .build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAttendanceByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                ApiResponse.<List<AttendanceResponse>>builder()
                        .success(true)
                        .message("Student attendance retrieved successfully")
                        .data(attendanceService.getAttendanceByStudent(studentId))
                        .build());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAttendanceByCourse(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(
                ApiResponse.<List<AttendanceResponse>>builder()
                        .success(true)
                        .message("Course attendance retrieved successfully")
                        .data(attendanceService.getAttendanceByCourse(courseId))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAttendance(
            @PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Attendance deleted successfully")
                        .data(null)
                        .build());
    }
}