package com.smartcampus.backend.service;

import com.smartcampus.backend.dto.*;

import java.util.List;

public interface NoticeService {

    NoticeResponse createNotice(NoticeRequest request);

    List<NoticeResponse> getAllNotices();

    List<NoticeResponse> getAudienceNotices(String audience);

    void deleteNotice(Long id);

}