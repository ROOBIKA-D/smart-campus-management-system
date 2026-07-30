package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.AttendanceRequest;
import com.smartcampus.backend.dto.AttendanceResponse;
import com.smartcampus.backend.entity.Attendance;
import com.smartcampus.backend.entity.Course;
import com.smartcampus.backend.entity.Student;
import com.smartcampus.backend.exception.ResourceNotFoundException;
import com.smartcampus.backend.repository.AttendanceRepository;
import com.smartcampus.backend.repository.CourseRepository;
import com.smartcampus.backend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public AttendanceResponse markAttendance(AttendanceRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));

        Attendance attendance = Attendance.builder()
                .student(student)
                .course(course)
                .attendanceDate(request.getAttendanceDate())
                .status(request.getStatus())
                .build();

        attendanceRepository.save(attendance);

        return mapToResponse(attendance);
    }

    @Override
    public List<AttendanceResponse> getAllAttendance() {

        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AttendanceResponse> getAttendanceByStudent(Long studentId) {

        return attendanceRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AttendanceResponse> getAttendanceByCourse(Long courseId) {

        return attendanceRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void deleteAttendance(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Attendance not found"));

        attendanceRepository.delete(attendance);
    }

    private AttendanceResponse mapToResponse(Attendance attendance) {

        return AttendanceResponse.builder()
                .id(attendance.getId())
                .studentId(attendance.getStudent().getId())
                .studentName(attendance.getStudent().getName())
                .courseId(attendance.getCourse().getId())
                .courseName(attendance.getCourse().getCourseName())
                .attendanceDate(attendance.getAttendanceDate())
                .status(attendance.getStatus())
                .build();
    }
}