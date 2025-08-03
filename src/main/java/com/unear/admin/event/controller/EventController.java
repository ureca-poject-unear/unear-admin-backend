package com.unear.admin.event.controller;

import com.unear.admin.common.docs.event.EventDocs;
import com.unear.admin.common.response.ApiResponse;
import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.event.dto.request.EventRequestDto;
import com.unear.admin.event.dto.request.PartnerStoreRequest;
import com.unear.admin.event.dto.response.EventDetailResponseDto;
import com.unear.admin.event.service.EventService;

import com.unear.admin.places.dto.requestdto.PlaceRequestDto;
import com.unear.admin.places.dto.responsedto.PlaceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.unear.admin.common.message.ResponseMessage.POPUP_STORE_REGISTERED;


@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @EventDocs.PostEvent
    @PostMapping    // 이벤트 지역 지정
    public ResponseEntity<Long> registerBaseEvent(@RequestBody EventRequestDto eventDto) {
        Long eventId = eventService.createBaseEvent(eventDto);
        return ResponseEntity.ok(eventId);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<ApiResponse<EventDetailResponseDto>> getEventInfo(@PathVariable Long eventId) {
        EventDetailResponseDto eventInfo = eventService.getEventById(eventId);
        return ResponseEntity.ok(ApiResponse.success(eventInfo));
    }

    // 2-1: 팝업스토어 등록
    @PostMapping("/{eventId}/places/popup")
    public ResponseEntity<ApiResponse<String>> registerPopupStore(
            @PathVariable Long eventId,
            @RequestBody PlaceRequestDto popupDto
    ) {
        eventService.registerPopupStore(eventId, popupDto);
        return ResponseEntity.ok(ApiResponse.success(POPUP_STORE_REGISTERED));
    }

    // 2-2: 반경 내 제휴처 조회
    @GetMapping("/{eventId}/partners/nearby")
    public ResponseEntity<ApiResponse<List<PlaceResponseDto>>> getNearbyPartnerStores(
            @PathVariable Long eventId
    ) {
        List<PlaceResponseDto> storeList = eventService.findNearbyPartnerStores(eventId);
        return ResponseEntity.ok(ApiResponse.success(storeList));
    }

    // 2-3: 제휴처 등록
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
}

