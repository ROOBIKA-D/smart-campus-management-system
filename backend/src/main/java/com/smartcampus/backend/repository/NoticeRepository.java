package com.smartcampus.backend.repository;

import com.smartcampus.backend.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice,Long>{

    List<Notice> findByAudience(String audience);

}