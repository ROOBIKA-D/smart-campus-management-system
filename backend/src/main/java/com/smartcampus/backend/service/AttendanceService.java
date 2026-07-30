package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.*;

import java.util.List;

public interface AttendanceService {

    AttendanceResponse markAttendance(AttendanceRequest request);

    List<AttendanceResponse> getAllAttendance();

    List<AttendanceResponse> getAttendanceByStudent(Long studentId);

    List<AttendanceResponse> getAttendanceByCourse(Long courseId);

    void deleteAttendance(Long id);

}