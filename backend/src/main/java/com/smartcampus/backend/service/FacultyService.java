package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.*;

import java.util.List;

public interface FacultyService {

    FacultyResponse createFaculty(FacultyRequest request);

    List<FacultyResponse> getAllFaculty();

    FacultyResponse getFacultyById(Long id);

    FacultyResponse updateFaculty(Long id,FacultyRequest request);

    void deleteFaculty(Long id);

    List<FacultyResponse> searchFaculty(String name);

}