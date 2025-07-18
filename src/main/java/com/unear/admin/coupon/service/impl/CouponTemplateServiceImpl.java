package com.unear.admin.coupon.service.impl;

import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.coupon.repository.CouponTemplateRepository;
import com.unear.admin.coupon.service.CouponTemplateService;
import com.unear.admin.event.entity.Event;
import com.unear.admin.event.repository.EventRepository;
import com.unear.admin.exception.BusinessException;
import com.unear.admin.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponTemplateServiceImpl implements CouponTemplateService {

    private final CouponTemplateRepository couponTemplateRepository;
    private final EventRepository eventRepository;


    @Override
    public void saveCouponTemplate(Long eventId, CouponTemplateRequestDto dto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EVENT_NOT_FOUND));

        couponTemplateRepository.save(dto.toEntity(event));
    }

}
