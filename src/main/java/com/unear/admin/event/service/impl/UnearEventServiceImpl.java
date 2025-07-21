package com.unear.admin.event.service.impl;

import com.unear.admin.common.enums.EventType;
import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.coupon.entity.CouponTemplate;
import com.unear.admin.coupon.repository.CouponTemplateRepository;
import com.unear.admin.event.dto.request.EventRequestDto;
import com.unear.admin.event.dto.request.EventPlaceRegistrationRequest;
import com.unear.admin.event.entity.Event;
import com.unear.admin.event.repository.EventRepository;
import com.unear.admin.eventplace.entity.EventPlace;
import com.unear.admin.eventplace.repository.EventPlaceRepository;
import com.unear.admin.event.service.EventService;
import com.unear.admin.places.dto.requestdto.PlaceRequestDto;
import com.unear.admin.places.entity.Place;
import com.unear.admin.places.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UnearEventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final EventPlaceRepository eventPlaceRepository;
    private final CouponTemplateRepository couponTemplateRepository;

    @Override
    public Long createBaseEvent(EventRequestDto dto) {
        Event event = Event.fromDto(dto);
        return eventRepository.save(event).getUnearEventsId();
    }

    @Override
    public void addCouponToEvent(Long eventId, CouponTemplateRequestDto dto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("이벤트를 찾을 수 없습니다."));
        CouponTemplate coupon = dto.toEntity(event);
        couponTemplateRepository.save(coupon);
    }

    @Override
    public void registerPopupAndPartners(Long eventId, EventPlaceRegistrationRequest request) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("이벤트를 찾을 수 없습니다."));

        // 1. 팝업스토어 저장 및 이벤트 등록
        PlaceRequestDto popupDto = request.popupStore();
        popupDto.setEventCode(EventType.REQUIRE);
        Place popupPlace = popupDto.toEntity();
        placeRepository.save(popupPlace);

        EventPlace popupMapping = EventPlace.builder()
                .event(event)
                .place(popupPlace)
                .eventCode(EventType.REQUIRE)
                .build();
        eventPlaceRepository.save(popupMapping);

        // 2. 제휴처 저장 및 이벤트 등록
        List<Long> partnerIds = request.partnerPlaceIds();
        for (Long partnerId : partnerIds) {
            Place partner = placeRepository.findById(partnerId)
                    .orElseThrow(() -> new IllegalArgumentException("제휴처가 존재하지 않습니다. ID: " + partnerId));

            partner.setEventCode(EventType.GENERAL); // 💡 자동 분기

            EventPlace partnerMapping = EventPlace.builder()
                    .event(event)
                    .place(partner)
                    .eventCode(EventType.GENERAL)
                    .build();
            eventPlaceRepository.save(partnerMapping);
        }
    }

}
