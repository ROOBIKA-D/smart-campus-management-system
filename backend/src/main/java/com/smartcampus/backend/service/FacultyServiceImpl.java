package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.FacultyRequest;
import com.smartcampus.backend.dto.FacultyResponse;
import com.smartcampus.backend.entity.Faculty;
import com.smartcampus.backend.repository.FacultyRepository;
import com.smartcampus.backend.exception.ResourceNotFoundException;
import com.smartcampus.backend.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Override
    public FacultyResponse createFaculty(FacultyRequest request) {

        if(facultyRepository.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException("Email already exists");
        }

        if(facultyRepository.existsByFacultyId(request.getFacultyId())){
            throw new DuplicateResourceException("Faculty ID already exists");
        }

        Faculty faculty = Faculty.builder()
                .facultyId(request.getFacultyId())
                .name(request.getName())
                .email(request.getEmail())
                .department(request.getDepartment())
                .designation(request.getDesignation())
                .phone(request.getPhone())
                .build();

        facultyRepository.save(faculty);

        return mapToResponse(faculty);
    }

    @Override
    public List<FacultyResponse> getAllFaculty() {
        return facultyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public FacultyResponse getFacultyById(Long id) {

        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found"));

        return mapToResponse(faculty);
    }

    @Override
    public FacultyResponse updateFaculty(Long id, FacultyRequest request) {

        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found"));

        faculty.setFacultyId(request.getFacultyId());
        faculty.setName(request.getName());
        faculty.setEmail(request.getEmail());
        faculty.setDepartment(request.getDepartment());
        faculty.setDesignation(request.getDesignation());
        faculty.setPhone(request.getPhone());

        facultyRepository.save(faculty);

        return mapToResponse(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {

        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found"));

        facultyRepository.delete(faculty);
    }

    @Override
    public List<FacultyResponse> searchFaculty(String name) {

        return facultyRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private FacultyResponse mapToResponse(Faculty faculty) {

        return FacultyResponse.builder()
                .id(faculty.getId())
                .facultyId(faculty.getFacultyId())
                .name(faculty.getName())
                .email(faculty.getEmail())
                .department(faculty.getDepartment())
                .designation(faculty.getDesignation())
                .phone(faculty.getPhone())
                .build();
    }
}