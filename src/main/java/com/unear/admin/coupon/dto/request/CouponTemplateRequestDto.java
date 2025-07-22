package com.unear.admin.coupon.dto.request;


import com.unear.admin.common.enums.DiscountPolicy;
import com.unear.admin.common.enums.MembershipGrade;
import com.unear.admin.common.enums.PlaceType;
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

    private Integer remainingQuantity;

    private LocalDate couponStart;
    private LocalDate couponEnd;

    private DiscountPolicy discountCode;
    private PlaceType markerCode;
    private MembershipGrade membershipCode;

    public CouponTemplate toEntity(Event event) {
        return CouponTemplate.builder()
                .couponName(couponName)
                .remainingQuantity(remainingQuantity)
                .couponStart(couponStart)
                .couponEnd(couponEnd)
                .discountCode(discountCode)
                .markerCode(markerCode)
                .membershipCode(membershipCode)
                .event(event)
                .build();
    }
}
