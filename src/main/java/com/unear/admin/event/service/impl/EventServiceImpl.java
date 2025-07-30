package com.unear.admin.event.service.impl;

import com.unear.admin.common.enums.EventType;
import com.unear.admin.common.exception.BusinessException;
import com.unear.admin.common.exception.ErrorCode;
import com.unear.admin.common.util.LocationUtils;
import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.coupon.entity.CouponTemplate;
import com.unear.admin.coupon.repository.CouponTemplateRepository;
import com.unear.admin.event.dto.request.EventRequestDto;
import com.unear.admin.event.dto.response.EventDetailResponseDto;
import com.unear.admin.event.entity.Event;
import com.unear.admin.event.repository.EventRepository;
import com.unear.admin.eventplace.entity.EventPlace;
import com.unear.admin.eventplace.repository.EventPlaceRepository;
import com.unear.admin.places.dto.requestdto.PlaceRequestDto;
import com.unear.admin.places.dto.responsedto.PlaceResponseDto;
import com.unear.admin.places.entity.Place;
import com.unear.admin.places.repository.PlaceRepository;
import com.unear.admin.event.service.EventService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final EventPlaceRepository eventPlaceRepository;
    private final CouponTemplateRepository couponTemplateRepository;

    //이벤트 기본 정보 등록
    @Override
    public Long createBaseEvent(EventRequestDto dto) {

        LocalDate today = LocalDate.now();

        boolean isActive = !dto.getStartAt().isAfter(today) && !dto.getEndAt().isBefore(today);

        Event event = Event.builder()
                .eventName(dto.getEventName())
                .eventDescription(dto.getEventDescription())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .radiusMeter(dto.getRadiusMeter())
                .startAt(dto.getStartAt())
                .endAt(dto.getEndAt())
                .isActive(isActive)
                .build();

        return eventRepository.save(event).getUnearEventsId();
    }

    //팝업스토어 등록 (이벤트 반경 내인지 확인)
    @Override
    public void registerPopupStore(Long eventId, PlaceRequestDto popupDto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_EVENT));

        Place popupStore = placeRepository.save(popupDto.toEntity());
        popupStore.setEventCode(EventType.REQUIRE);

        double distance = LocationUtils.calculateDistance(
                event.getLatitude().doubleValue(), event.getLongitude().doubleValue(),
                popupStore.getLatitude().doubleValue(), popupStore.getLongitude().doubleValue()
        );

        if (distance > event.getRadiusMeter()) {
            throw new BusinessException(ErrorCode.OUT_OF_EVENT_RADIUS);
        }

        // 1. 팝업스토어와 이벤트 연결 (event_places)
        EventPlace popup = EventPlace.builder()
                .event(event)
                .place(popupStore)
                .eventCode(EventType.REQUIRE) // 필수 방문 매장
                .build();

        eventPlaceRepository.save(popup);

        //필드에 해당 팝업스토어 ID 저장
        event.setPopupStore(popupStore);
        eventRepository.save(event);
    }

    //반경 내 제휴처 조회
    @Override
    public List<PlaceResponseDto> findNearbyPartnerStores(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_EVENT));

        return placeRepository.findAll().stream()
                .filter(p -> p.getEventCode() == EventType.NONE)
                .filter(p -> LocationUtils.calculateDistance(
                        event.getLatitude().doubleValue(), event.getLongitude().doubleValue(),
                        p.getLatitude().doubleValue(), p.getLongitude().doubleValue()
                ) <= event.getRadiusMeter())
                .map(PlaceResponseDto::from)
                .toList();
    }

    //선택된 제휴처 저장
    @Override
    public void registerPartnerStores(Long eventId, List<Long> partnerStoreIds) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_EVENT));

        List<Place> places = placeRepository.findAllById(partnerStoreIds);

        for (Place place : places) {
            place.setEventCode(EventType.GENERAL); // 추가
        }
        placeRepository.saveAll(places);

        List<EventPlace> eventPlaces = places.stream()
                .map(place -> EventPlace.builder()
                        .event(event)
                        .place(place)
                        .eventCode(EventType.GENERAL)  // 제휴처는 GENERAL
                        .build())
                .toList();

        eventPlaceRepository.saveAll(eventPlaces);
    }


    //선착순 쿠폰 등록 및 이벤트 연동
    @Override
    public void addCouponToEvent(Long eventId, CouponTemplateRequestDto dto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_EVENT));

        CouponTemplate coupon = couponTemplateRepository.save(dto.toEntity(event));
        event.setCouponTemplate(coupon);
    }

}
