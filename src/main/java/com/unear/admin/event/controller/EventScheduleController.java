package com.unear.admin.event.controller;

import com.unear.admin.common.response.ApiResponse;
import com.unear.admin.event.service.EventScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events/schedule")
@RequiredArgsConstructor
public class EventScheduleController {

    private final EventScheduleService eventScheduleService;

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateEventSchedule() {
        eventScheduleService.activateTodayEvent();
        return ResponseEntity.ok(ApiResponse.success("이벤트 활성화 상태 갱신 완료"));
    }
}