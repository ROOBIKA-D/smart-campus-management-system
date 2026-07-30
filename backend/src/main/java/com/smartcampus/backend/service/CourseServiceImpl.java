package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.CourseRequest;
import com.smartcampus.backend.dto.CourseResponse;
import com.smartcampus.backend.entity.Course;
import com.smartcampus.backend.repository.CourseRepository;
import com.smartcampus.backend.exception.ResourceNotFoundException;
import com.smartcampus.backend.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseResponse createCourse(CourseRequest request) {

        if(courseRepository.existsByCourseCode(request.getCourseCode())){
            throw new DuplicateResourceException("Course Code already exists");
        }

        Course course = Course.builder()
                .courseCode(request.getCourseCode())
                .courseName(request.getCourseName())
                .department(request.getDepartment())
                .semester(request.getSemester())
                .credits(request.getCredits())
                .facultyId(request.getFacultyId())
                .build();

        courseRepository.save(course);

        return mapToResponse(course);
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CourseResponse getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        return mapToResponse(course);
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest request) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        course.setCourseCode(request.getCourseCode());
        course.setCourseName(request.getCourseName());
        course.setDepartment(request.getDepartment());
        course.setSemester(request.getSemester());
        course.setCredits(request.getCredits());
        course.setFacultyId(request.getFacultyId());

        courseRepository.save(course);

        return mapToResponse(course);
    }

    @Override
    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        courseRepository.delete(course);
    }

    @Override
    public List<CourseResponse> searchCourse(String courseName) {

        return courseRepository.findByCourseNameContainingIgnoreCase(courseName)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private CourseResponse mapToResponse(Course course) {

        return CourseResponse.builder()
                .id(course.getId())
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .department(course.getDepartment())
                .semester(course.getSemester())
                .credits(course.getCredits())
                .facultyId(course.getFacultyId())
                .build();
    }
}