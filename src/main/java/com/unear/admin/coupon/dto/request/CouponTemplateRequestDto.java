package com.unear.admin.coupon.dto.request;


import com.unear.admin.common.enums.DiscountPolicy;
import com.unear.admin.common.enums.MembershipGrade;
import com.unear.admin.common.enums.PlaceType;
import com.unear.admin.coupon.entity.CouponTemplate;
import com.unear.admin.event.entity.Event;
import com.unear.admin.common.exception.BusinessException;
import com.unear.admin.common.exception.ErrorCode;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponTemplateRequestDto {

    private String couponName;

    private Long discountPolicyId;
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
                .discountPolicyDetailId(discountPolicyId)
                .event(event)
                .build();
    }

    public void updateEntity(CouponTemplate entity) {
        if (discountCode == DiscountPolicy.COUPON_FCFS) {
            throw new BusinessException(ErrorCode.INVALID_COUPON_POLICY);
        }

        if (discountPolicyId == null) {
            throw new BusinessException(ErrorCode.INVALID_COUPON_POLICY);
        }

        entity.update(
                couponName,
                discountCode,
                remainingQuantity,
                couponStart,
                couponEnd,
                membershipCode,
                markerCode,
                discountPolicyId
        );
    }
}
