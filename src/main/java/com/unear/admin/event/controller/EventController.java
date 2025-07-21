package com.unear.admin.event.controller;

import com.unear.admin.common.response.ApiResponse;
import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.event.dto.request.EventPlaceRegistrationRequest;
import com.unear.admin.event.dto.request.EventRequestDto;
import com.unear.admin.event.service.EventService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;


    @PostMapping
    public ResponseEntity<Long> registerBaseEvent(@RequestBody EventRequestDto eventDto) {
        Long eventId = eventService.createBaseEvent(eventDto);
        return ResponseEntity.ok(eventId);
    }

    @PostMapping("/{eventId}/places")
    public ResponseEntity<ApiResponse<String>> registerPopupAndPartners(
            @PathVariable Long eventId,
            @RequestBody EventPlaceRegistrationRequest request
    ) {
        eventService.registerPopupAndPartners(eventId, request);
        return ResponseEntity.ok(ApiResponse.success("팝업스토어 및 제휴처 등록 완료"));
    }

    @PostMapping("/{eventId}/coupon")
    public ResponseEntity<Void> registerCoupon(@PathVariable Long eventId,
                                               @RequestBody CouponTemplateRequestDto couponDto) {
        eventService.addCouponToEvent(eventId, couponDto);
        return ResponseEntity.ok().build();
    }
}
