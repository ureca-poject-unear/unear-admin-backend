package com.unear.admin.event.service.impl;

import com.unear.admin.common.enums.EventType;
import com.unear.admin.common.exception.BusinessException;
import com.unear.admin.common.exception.ErrorCode;
import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.coupon.entity.CouponTemplate;
import com.unear.admin.coupon.repository.CouponTemplateRepository;
import com.unear.admin.event.dto.request.EventRequestDto;
import com.unear.admin.event.dto.response.EventDetailResponseDto;
import com.unear.admin.event.entity.Event;
import com.unear.admin.event.repository.EventRepository;
import com.unear.admin.event.service.EventActivationPolicy;
import com.unear.admin.event.service.EventService;
import com.unear.admin.eventplace.entity.EventPlace;
import com.unear.admin.eventplace.repository.EventPlaceRepository;
import com.unear.admin.places.dto.requestdto.PlaceRequestDto;
import com.unear.admin.places.dto.responsedto.PlaceResponseDto;
import com.unear.admin.places.entity.Place;
import com.unear.admin.places.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final EventPlaceRepository eventPlaceRepository;
    private final CouponTemplateRepository couponTemplateRepository;
    private final EventActivationPolicy activationPolicy;

    // 1. 이벤트 기본 정보 등록
    @Override
    public Long createBaseEvent(EventRequestDto dto) {
        LocalDate startAt = dto.getStartAt();
        LocalDate endAt = dto.getEndAt();

        // 기간 중복 확인
        boolean exists = eventRepository.existsEventDuringPeriod(startAt, endAt);
        if (exists) {
            throw new BusinessException(ErrorCode.DUPLICATE_EVENT_PERIOD);
        }

        boolean isActive = activationPolicy.shouldActivate(startAt, endAt);
        Event event = dto.toEntity(isActive);
        return eventRepository.save(event).getUnearEventsId();
    }

    // 2-1. 팝업스토어 등록
    @Override
    public void registerPopupStore(Long eventId, PlaceRequestDto popupDto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_EVENT));

        Place popupStore = placeRepository.save(popupDto.toEntity());
        popupStore.setEventCode(EventType.REQUIRE);

        if (!event.isWithinRadius(popupStore)) {
            throw new BusinessException(ErrorCode.OUT_OF_EVENT_RADIUS);
        }

        // event_places 테이블 연결
        EventPlace popup = EventPlace.builder()
                .event(event)
                .place(popupStore)
                .eventCode(EventType.REQUIRE)
                .build();
        eventPlaceRepository.save(popup);

        // 팝업스토어 필드 저장
        event.setPopupStore(popupStore);
        eventRepository.save(event);
    }

    // 2-2. 반경 내 제휴처 조회
    @Override
    public List<PlaceResponseDto> findNearbyPartnerStores(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_EVENT));

        return placeRepository.findWithinRadius(
                        event.getLatitude(),
                        event.getLongitude(),
                        event.getRadiusMeter()
                ).stream()
                .filter(p -> EventType.NONE.equals(p.getEventCode()))
                .map(PlaceResponseDto::from)
                .toList();
    }

    // 2-3. 제휴처 저장
    @Override
    public void registerPartnerStores(Long eventId, List<Long> partnerStoreIds) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_EVENT));

        List<Place> places = placeRepository.findAllById(partnerStoreIds);

        for (Place place : places) {
            place.setEventCode(EventType.GENERAL);
        }
        placeRepository.saveAll(places);

        List<EventPlace> eventPlaces = places.stream()
                .map(place -> EventPlace.builder()
                        .event(event)
                        .place(place)
                        .eventCode(EventType.GENERAL)
                        .build())
                .toList();

        eventPlaceRepository.saveAll(eventPlaces);
    }

    // 3. 선착순 쿠폰 등록 및 이벤트 연동
    @Override
    public void addCouponToEvent(Long eventId, CouponTemplateRequestDto dto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_EVENT));

        CouponTemplate coupon = dto.toEntity(event);
        couponTemplateRepository.save(coupon);
        event.assignCoupon(coupon);
    }

    @Override
    public EventDetailResponseDto getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EVENT_NOT_FOUND));

        return EventDetailResponseDto.of(event,null,null); // 또는 .of(event) 등 DTO 매핑 메서드
    }
}
