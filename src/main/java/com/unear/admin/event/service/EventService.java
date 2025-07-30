package com.unear.admin.event.service;

import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.event.dto.request.EventRequestDto;

import com.unear.admin.places.dto.requestdto.PlaceRequestDto;
import com.unear.admin.places.dto.responsedto.PlaceResponseDto;

import java.util.List;
import java.util.Optional;

public interface EventService {

    // 1단계: 이벤트 기본 정보 등록
    Long createBaseEvent(EventRequestDto dto);

    // 2단계-1: 팝업스토어 등록 (반경 내인지 검증)
    void registerPopupStore(Long eventId, PlaceRequestDto popupdto);

    // 2단계-2: 반경 내 제휴처 조회
    List<PlaceResponseDto> findNearbyPartnerStores(Long eventId);

    // 2단계-3: 선택된 제휴처 등록
    void registerPartnerStores(Long eventId, List<Long> partnerStoreIds);

    // 3단계: 선착순 쿠폰 등록 및 이벤트 연동
    void addCouponToEvent(Long eventId, CouponTemplateRequestDto dto);

}
