package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.AttendanceRequest;
import com.smartcampus.backend.dto.AttendanceResponse;
import com.smartcampus.backend.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public AttendanceResponse markAttendance(@Valid @RequestBody AttendanceRequest request) {
        return attendanceService.markAttendance(request);
    }

    @GetMapping
    public List<AttendanceResponse> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/student/{studentId}")
    public List<AttendanceResponse> getAttendanceByStudent(@PathVariable Long studentId) {
        return attendanceService.getAttendanceByStudent(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<AttendanceResponse> getAttendanceByCourse(@PathVariable Long courseId) {
        return attendanceService.getAttendanceByCourse(courseId);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }
}