package com.unear.admin.coupon.service;

import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;

public interface CouponTemplateService {
    void saveCouponTemplate(Long eventId, CouponTemplateRequestDto dto);
}
