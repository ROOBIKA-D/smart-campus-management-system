package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.CourseRequest;
import com.smartcampus.backend.dto.CourseResponse;
import com.smartcampus.backend.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public CourseResponse createCourse(@Valid @RequestBody CourseRequest request){
        return courseService.createCourse(request);
    }

    @GetMapping
    public List<CourseResponse> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable Long id,
                                       @Valid @RequestBody CourseRequest request){
        return courseService.updateCourse(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
    }

    @GetMapping("/search")
    public List<CourseResponse> searchCourse(@RequestParam String courseName){
        return courseService.searchCourse(courseName);
    }
}