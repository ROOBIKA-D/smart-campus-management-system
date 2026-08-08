package com.smartcampus.backend.service.impl;

import com.smartcampus.backend.dto.StudentRequest;
import com.smartcampus.backend.dto.StudentResponse;
import com.smartcampus.backend.entity.Student;
import com.smartcampus.backend.repository.StudentRepository;
import com.smartcampus.backend.service.StudentService;
import com.smartcampus.backend.exception.ResourceNotFoundException;
import com.smartcampus.backend.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentResponse createStudent(StudentRequest request) {

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (studentRepository.existsByRollNumber(request.getRollNumber())) {
            throw new DuplicateResourceException("Roll Number already exists");
        }

        Student student = Student.builder()
                .rollNumber(request.getRollNumber())
                .name(request.getName())
                .email(request.getEmail())
                .department(request.getDepartment())
                .year(request.getYear())
                .phone(request.getPhone())
                .build();

        Student savedStudent = studentRepository.save(student);

        return mapToResponse(savedStudent);
    }

    @Override
    public Page<StudentResponse> getAllStudents(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return studentRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public StudentResponse getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        return mapToResponse(student);
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest request) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setRollNumber(request.getRollNumber());
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setDepartment(request.getDepartment());
        student.setYear(request.getYear());
        student.setPhone(request.getPhone());

        Student updatedStudent = studentRepository.save(student);

        return mapToResponse(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        studentRepository.delete(student);
    }

    private StudentResponse mapToResponse(Student student) {

        return StudentResponse.builder()
                .id(student.getId())
                .rollNumber(student.getRollNumber())
                .name(student.getName())
                .email(student.getEmail())
                .department(student.getDepartment())
                .year(student.getYear())
                .phone(student.getPhone())
                .build();
    }
    @Override
    public List<StudentResponse> searchStudents(String name) {

        return studentRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}