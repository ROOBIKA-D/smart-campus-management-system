package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.StudentRequest;
import com.smartcampus.backend.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentRequest request);

    List<StudentResponse> getAllStudents();

    StudentResponse getStudentById(Long id);

    StudentResponse updateStudent(Long id, StudentRequest request);

    void deleteStudent(Long id);

    List<StudentResponse> searchStudents(String name);
}