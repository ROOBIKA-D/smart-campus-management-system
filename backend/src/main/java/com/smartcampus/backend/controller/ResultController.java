package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.*;
import com.smartcampus.backend.service.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping
    public ResultResponse publishResult(@Valid @RequestBody ResultRequest request){
        return resultService.publishResult(request);
    }

    @GetMapping
    public List<ResultResponse> getAllResults(){
        return resultService.getAllResults();
    }

    @GetMapping("/student/{studentId}")
    public List<ResultResponse> getStudentResults(@PathVariable Long studentId){
        return resultService.getStudentResults(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<ResultResponse> getCourseResults(@PathVariable Long courseId){
        return resultService.getCourseResults(courseId);
    }

    @DeleteMapping("/{id}")
    public void deleteResult(@PathVariable Long id){
        resultService.deleteResult(id);
    }

} 