package com.smartcampus.backend.repository;

import com.smartcampus.backend.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty,Long>{

    Optional<Faculty> findByEmail(String email);

    Optional<Faculty> findByFacultyId(String facultyId);

    boolean existsByEmail(String email);

    boolean existsByFacultyId(String facultyId);

    List<Faculty> findByNameContainingIgnoreCase(String name);
}