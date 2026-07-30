package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.*;
import com.smartcampus.backend.entity.Notice;
import com.smartcampus.backend.repository.NoticeRepository;
import com.smartcampus.backend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public NoticeResponse createNotice(NoticeRequest request) {

        Notice notice = Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .audience(request.getAudience())
                .postedBy(request.getPostedBy())
                .postedOn(LocalDateTime.now())
                .build();

        noticeRepository.save(notice);

        return mapToResponse(notice);
    }

    @Override
    public List<NoticeResponse> getAllNotices() {

        return noticeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<NoticeResponse> getAudienceNotices(String audience) {

        return noticeRepository.findByAudience(audience)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void deleteNotice(Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notice not found"));

        noticeRepository.delete(notice);
    }

    private NoticeResponse mapToResponse(Notice notice){

        return NoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .audience(notice.getAudience())
                .postedBy(notice.getPostedBy())
                .postedOn(notice.getPostedOn())
                .build();
    }
}