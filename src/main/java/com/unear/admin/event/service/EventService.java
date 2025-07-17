package com.unear.admin.event.service;

import com.unear.admin.coupon.dto.CouponTemplateRequestDto;
import com.unear.admin.event.dto.EventRequestDto;
import com.unear.admin.places.dto.PlaceRequestDto;

public interface EventService {

    Long createBaseEvent(EventRequestDto dto);

    void addPlaceToEvent(Long eventId, PlaceRequestDto dto);

    void addCouponToEvent(Long eventId, CouponTemplateRequestDto dto);
}
