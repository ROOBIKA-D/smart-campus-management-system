package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.*;

import java.util.List;

public interface ResultService {

    ResultResponse publishResult(ResultRequest request);

    List<ResultResponse> getAllResults();

    List<ResultResponse> getStudentResults(Long studentId);

    List<ResultResponse> getCourseResults(Long courseId);

    void deleteResult(Long id);

}