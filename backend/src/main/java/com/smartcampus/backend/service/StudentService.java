package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.StudentRequest;
import com.smartcampus.backend.dto.StudentResponse;
import org.springframework.data.domain.Page;
import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentRequest request);

    Page<StudentResponse> getAllStudents(
        int page,
        int size,
        String sortBy,
        String direction
    );

    StudentResponse getStudentById(Long id);

    StudentResponse updateStudent(Long id, StudentRequest request);

    void deleteStudent(Long id);

    List<StudentResponse> searchStudents(String name);
}