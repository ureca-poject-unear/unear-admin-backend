package com.unear.admin.event.service.impl;

import com.unear.admin.coupon.dto.CouponTemplateRequestDto;
import com.unear.admin.coupon.entity.CouponTemplate;
import com.unear.admin.coupon.repository.CouponTemplateRepository;
import com.unear.admin.event.dto.EventRequestDto;
import com.unear.admin.event.entity.Event;
import com.unear.admin.event.repository.EventRepository;
import com.unear.admin.event.service.EventService;
import com.unear.admin.places.dto.PlaceRequestDto;
import com.unear.admin.places.entity.Place;
import com.unear.admin.places.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UnearEventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final CouponTemplateRepository couponTemplateRepository;

    @Override
    public Long createBaseEvent(EventRequestDto dto) {
        Event event = Event.fromDto(dto);
        return eventRepository.save(event).getUnearEventsId();
    }

    @Override
    public void addPlaceToEvent(Long eventId, PlaceRequestDto dto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("이벤트를 찾을 수 없습니다."));
        Place place = dto.toEntity(event);
        placeRepository.save(place);
    }

    @Override
    public void addCouponToEvent(Long eventId, CouponTemplateRequestDto dto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("이벤트를 찾을 수 없습니다."));
        CouponTemplate coupon = dto.toEntity(event);
        couponTemplateRepository.save(coupon);
    }
}
