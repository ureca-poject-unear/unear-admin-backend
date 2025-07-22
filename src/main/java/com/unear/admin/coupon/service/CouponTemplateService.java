package com.unear.admin.coupon.service;

import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.coupon.dto.response.CouponTemplateResponseDto;

import java.util.List;

public interface CouponTemplateService {
    void saveCouponTemplate(Long eventId, CouponTemplateRequestDto dto);
    void createGeneralCoupon(CouponTemplateRequestDto dto);

    // 이후 구현할 기능들
    List<CouponTemplateResponseDto> getAllCoupons();
    CouponTemplateResponseDto getCoupon(Long id);
    void updateCoupon(Long id, CouponTemplateRequestDto dto);
    void deleteCoupon(Long id);


}
