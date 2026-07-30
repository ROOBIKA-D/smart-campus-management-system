package com.smartcampus.backend.repository;

import com.smartcampus.backend.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Long>{

    List<Result> findByStudentId(Long studentId);

    List<Result> findByCourseId(Long courseId);

}