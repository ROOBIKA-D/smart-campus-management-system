package com.smartcampus.backend.controller;

import com.smartcampus.backend.dto.*;
import com.smartcampus.backend.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public NoticeResponse createNotice(@Valid @RequestBody NoticeRequest request){
        return noticeService.createNotice(request);
    }

    @GetMapping
    public List<NoticeResponse> getAllNotices(){
        return noticeService.getAllNotices();
    }

    @GetMapping("/audience/{audience}")
    public List<NoticeResponse> getAudienceNotices(@PathVariable String audience){
        return noticeService.getAudienceNotices(audience);
    }

    @DeleteMapping("/{id}")
    public void deleteNotice(@PathVariable Long id){
        noticeService.deleteNotice(id);
    }
}