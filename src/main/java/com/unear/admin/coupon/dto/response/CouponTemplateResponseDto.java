package com.unear.admin.coupon.dto.response;

import com.unear.admin.common.enums.DiscountPolicy;
import com.unear.admin.coupon.entity.CouponTemplate;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CouponTemplateResponseDto(
        Long couponId,
        String couponName,
        DiscountPolicy discountPolicy,
        String discountPolicyLabel,
        Integer remainingQuantity,
        LocalDateTime couponStart,
        LocalDateTime couponEnd
) {
    public static CouponTemplateResponseDto from(CouponTemplate coupon) {
        return CouponTemplateResponseDto.builder()
                .couponId(coupon.getCouponTemplateId())
                .couponName(coupon.getCouponName())
                .discountPolicy(coupon.getDiscountCode())
                .discountPolicyLabel(coupon.getDiscountCode().getLabel())
                .remainingQuantity(coupon.getRemainingQuantity())
                .couponStart(coupon.getCouponStart())
                .couponEnd(coupon.getCouponEnd())
                .build();
    }
}
