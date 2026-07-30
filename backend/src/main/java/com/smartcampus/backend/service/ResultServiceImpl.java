package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.*;
import com.smartcampus.backend.entity.*;
import com.smartcampus.backend.repository.*;
import com.smartcampus.backend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public ResultResponse publishResult(ResultRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new  ResourceNotFoundException("Course not found"));

        int total = request.getInternalMarks() + request.getExternalMarks();

        Grade grade;

        if(total>=90) grade=Grade.O;
        else if(total>=80) grade=Grade.A_PLUS;
        else if(total>=70) grade=Grade.A;
        else if(total>=60) grade=Grade.B_PLUS;
        else if(total>=50) grade=Grade.B;
        else if(total>=40) grade=Grade.C;
        else grade=Grade.U;

        boolean pass = total>=40;

        Result result = Result.builder()
                .student(student)
                .course(course)
                .internalMarks(request.getInternalMarks())
                .externalMarks(request.getExternalMarks())
                .totalMarks(total)
                .grade(grade)
                .pass(pass)
                .build();

        resultRepository.save(result);

        return mapToResponse(result);
    }

    @Override
    public List<ResultResponse> getAllResults() {
        return resultRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public List<ResultResponse> getStudentResults(Long studentId) {
        return resultRepository.findByStudentId(studentId)
                .stream().map(this::mapToResponse).toList();
    }

    @Override
    public List<ResultResponse> getCourseResults(Long courseId) {
        return resultRepository.findByCourseId(courseId)
                .stream().map(this::mapToResponse).toList();
    }

    @Override
    public void deleteResult(Long id) {

        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));

        resultRepository.delete(result);
    }

    private ResultResponse mapToResponse(Result result){

        return ResultResponse.builder()
                .id(result.getId())
                .studentId(result.getStudent().getId())
                .studentName(result.getStudent().getName())
                .courseId(result.getCourse().getId())
                .courseName(result.getCourse().getCourseName())
                .internalMarks(result.getInternalMarks())
                .externalMarks(result.getExternalMarks())
                .totalMarks(result.getTotalMarks())
                .grade(result.getGrade())
                .pass(result.getPass())
                .build();
    }

}