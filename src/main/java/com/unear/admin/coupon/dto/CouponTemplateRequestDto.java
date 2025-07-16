package com.unear.admin.coupon.dto;

import com.unear.admin.common.enums.DiscountPolicy;
import com.unear.admin.coupon.entity.CouponTemplate;
import com.unear.admin.event.entity.Event;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponTemplateRequestDto {

    private String couponName;
    private String description;
    private String discountPolicy;
    private Integer remainingQuantity;

    private LocalDate couponStart;
    private LocalDate couponEnd;

    @Builder.Default
    private DiscountPolicy discountCode = DiscountPolicy.COUPON_FCFS;

    public CouponTemplate toEntity(Event event) {
        return CouponTemplate.builder()
                .couponName(couponName)
                .description(description)
                .discountPolicy(discountPolicy)
                .remainingQuantity(remainingQuantity)
                .couponStart(couponStart)
                .couponEnd(couponEnd)
                .event(event)
                .build();
    }
}
