package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.*;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CourseRequest request);

    List<CourseResponse> getAllCourses();

    CourseResponse getCourseById(Long id);

    CourseResponse updateCourse(Long id, CourseRequest request);

    void deleteCourse(Long id);

    List<CourseResponse> searchCourse(String courseName);

}