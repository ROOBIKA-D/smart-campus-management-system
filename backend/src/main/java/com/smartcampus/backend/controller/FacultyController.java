package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.FacultyRequest;
import com.smartcampus.backend.dto.FacultyResponse;
import com.smartcampus.backend.service.FacultyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @PostMapping
    public FacultyResponse createFaculty(@Valid @RequestBody FacultyRequest request){
        return facultyService.createFaculty(request);
    }

    @GetMapping
    public List<FacultyResponse> getAllFaculty(){
        return facultyService.getAllFaculty();
    }

    @GetMapping("/{id}")
    public FacultyResponse getFacultyById(@PathVariable Long id){
        return facultyService.getFacultyById(id);
    }

    @PutMapping("/{id}")
    public FacultyResponse updateFaculty(@PathVariable Long id,
                                         @Valid @RequestBody FacultyRequest request){
        return facultyService.updateFaculty(id,request);
    }

    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id){
        facultyService.deleteFaculty(id);
    }

    @GetMapping("/search")
    public List<FacultyResponse> searchFaculty(@RequestParam String name){
        return facultyService.searchFaculty(name);
    }
}