package com.unear.admin.coupon.dto.response;

import com.unear.admin.common.enums.DiscountPolicy;
import com.unear.admin.coupon.entity.CouponTemplate;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CouponTemplateResponseDto(
        Long couponId,
        String couponName,
        String description,
        DiscountPolicy discountPolicy,
        String discountPolicyLabel,
        Integer remainingQuantity,
        LocalDate couponStart,
        LocalDate couponEnd
) {
    public static CouponTemplateResponseDto from(CouponTemplate coupon) {
        return CouponTemplateResponseDto.builder()
                .couponId(coupon.getCouponTemplateId())
                .couponName(coupon.getCouponName())
                .discountPolicy(DiscountPolicy.valueOf(coupon.getDiscountPolicy().getLabel()))
                .remainingQuantity(coupon.getRemainingQuantity())
                .couponStart(coupon.getCouponStart())
                .couponEnd(coupon.getCouponEnd())
                .build();
    }
}
