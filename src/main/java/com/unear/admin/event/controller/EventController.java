package com.unear.admin.event.controller;

import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.event.dto.request.EventRequestDto;
import com.unear.admin.event.service.EventService;
import com.unear.admin.places.dto.requestdto.PlaceRequestDto;
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
    public ResponseEntity<Void> registerPlace(@PathVariable Long eventId,
                                              @RequestBody PlaceRequestDto placeDto) {
        eventService.addPlaceToEvent(eventId, placeDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{eventId}/coupon")
    public ResponseEntity<Void> registerCoupon(@PathVariable Long eventId,
                                               @RequestBody CouponTemplateRequestDto couponDto) {
        eventService.addCouponToEvent(eventId, couponDto);
        return ResponseEntity.ok().build();
    }
}
