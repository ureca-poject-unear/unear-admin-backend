package com.unear.admin.event.service;

import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.event.dto.request.EventPlaceRegistrationRequest;
import com.unear.admin.event.dto.request.EventRequestDto;
import com.unear.admin.places.dto.requestdto.PlaceRequestDto;

public interface EventService {

    Long createBaseEvent(EventRequestDto dto);


    void addCouponToEvent(Long eventId, CouponTemplateRequestDto dto);

    void registerPopupAndPartners(Long eventId, EventPlaceRegistrationRequest request);
}
