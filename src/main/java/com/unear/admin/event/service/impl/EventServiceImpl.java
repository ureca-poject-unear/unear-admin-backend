package com.unear.admin.event.service.impl;

import com.unear.admin.common.enums.EventType;
import com.unear.admin.common.exception.BusinessException;
import com.unear.admin.common.exception.ErrorCode;
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
public class EventServiceImpl implements EventService {

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
    public void addPlaceToEvent(Long eventId, EventPlaceRegistrationRequest dto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EVENT_NOT_FOUND));


        PlaceRequestDto popupDto = dto.popupStore();
        popupDto.setEventCode(EventType.REQUIRE);         // 팝업스토어는 REQUIRE
        Place popupStore = popupDto.toEntity();           // 새 엔티티 생성
        placeRepository.save(popupStore);                 // 저장

        EventPlace popupMapping = EventPlace.builder()
                .event(event)
                .place(popupStore)
                .eventCode(EventType.REQUIRE)
                .build();
        eventPlaceRepository.save(popupMapping);

        List<Place> nearbyPlaces = placeRepository.findWithinRadius(
                event.getLatitude(),
                event.getLongitude(),
                event.getRadiusMeter()
        );

        for (Place partner : nearbyPlaces) {

            if (isSameLocation(partner, popupStore)) continue;

            partner.setEventCode(EventType.GENERAL);
            placeRepository.save(partner);

            EventPlace partnerMapping = EventPlace.builder()
                    .event(event)
                    .place(partner)
                    .eventCode(EventType.GENERAL)
                    .build();
            eventPlaceRepository.save(partnerMapping);
        }

    }
    private boolean isSameLocation(Place a, Place b) {
        if (a.getLatitude() == null || a.getLongitude() == null ||
            b.getLatitude() == null || b.getLongitude() == null) {
            return false;
        }

        BigDecimal tolerance = new BigDecimal("0.000001"); // 약 0.1미터 정밀도
        return a.getLatitude().subtract(b.getLatitude()).abs().compareTo(tolerance) <= 0 &&
               a.getLongitude().subtract(b.getLongitude()).abs().compareTo(tolerance) <= 0;
    }
}
