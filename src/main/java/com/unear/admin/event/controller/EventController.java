package com.unear.admin.event.controller;

import com.unear.admin.common.docs.event.EventDocs;
import com.unear.admin.common.response.ApiResponse;
import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.event.dto.request.EventPlaceRegistrationRequest;
import com.unear.admin.event.dto.request.EventRequestDto;
import com.unear.admin.event.dto.request.PartnerStoreRequest;
import com.unear.admin.event.dto.response.EventDetailResponseDto;
import com.unear.admin.event.service.EventService;

import com.unear.admin.event.service.impl.EventServiceImpl;
import com.unear.admin.places.dto.requestdto.PlaceRequestDto;
import com.unear.admin.places.dto.responsedto.PlaceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventServiceImpl eventServiceImpl;

    @EventDocs.PostEvent
    @PostMapping    // 이벤트 지역 지정
    public ResponseEntity<Long> registerBaseEvent(@RequestBody EventRequestDto eventDto) {
        Long eventId = eventService.createBaseEvent(eventDto);
        return ResponseEntity.ok(eventId);
    }

    // ✅ 2단계-1: 팝업스토어 등록
    @PostMapping("/{eventId}/places/popup")
    public ResponseEntity<ApiResponse<String>> registerPopupStore(
            @PathVariable Long eventId,
            @RequestBody PlaceRequestDto popupDto
    ) {
        eventService.registerPopupStore(eventId, popupDto);
        return ResponseEntity.ok(ApiResponse.success("팝업스토어 등록 완료"));
    }

    // ✅ 2단계-2: 반경 내 제휴처 조회
    @GetMapping("/{eventId}/partners/nearby")
    public ResponseEntity<ApiResponse<List<PlaceResponseDto>>> getNearbyPartnerStores(
            @PathVariable Long eventId
    ) {
        List<PlaceResponseDto> storeList = eventService.findNearbyPartnerStores(eventId);
        return ResponseEntity.ok(ApiResponse.success(storeList));
    }

    // ✅ 2단계-3: 제휴처 등록
    @PostMapping("/{eventId}/partners")
    public ResponseEntity<Void> registerPartnerStores(@PathVariable Long eventId,
                                                      @RequestBody PartnerStoreRequest request) {
        eventService.registerPartnerStores(eventId, request.partnerStoreIds());
        return ResponseEntity.ok().build();
    }

    @EventDocs.PostEventCoupon
    @PostMapping("/{eventId}/coupon")
    public ResponseEntity<Void> registerCoupon(@PathVariable Long eventId,
                                               @RequestBody CouponTemplateRequestDto couponDto) {
        eventService.addCouponToEvent(eventId, couponDto);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/current")
//    public ResponseEntity<ApiResponse<EventDetailResponseDto>> getCurrentEvent() {
//        return eventServiceImpl.getCurrentEvent()
//                .map(event -> {
//                    EventDetailResponseDto dto = EventDetailResponseDto.from(event);
//                    return ResponseEntity.ok(ApiResponse.success(dto));
//                })
//                .orElse(ResponseEntity.ok(ApiResponse.success(null))); // 이벤트 없음 처리
//    }
}

