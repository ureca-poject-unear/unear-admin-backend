package com.unear.admin.coupon.entity;

import com.unear.admin.common.enums.DiscountPolicy;
import com.unear.admin.common.enums.MembershipGrade;
import com.unear.admin.common.enums.PlaceType;
import com.unear.admin.event.entity.Event;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "coupon_templates")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CouponTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponTemplateId;

    private String couponName;

    private Long discountPolicyDetailId;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_code", nullable = false)
    private DiscountPolicy discountCode;

    private Integer remainingQuantity;

    private LocalDate couponStart;

    private LocalDate couponEnd;

    @Enumerated(EnumType.STRING)
    @Column(name = "marker_code", nullable = false)
    private PlaceType markerCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_code", nullable = false)
    private MembershipGrade membershipCode;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isDeleted;

    /**
     * 도메인 내에서 업데이트 로직을 수행할 수 있도록 캡슐화
     */
    public void updateFromDto(
            String couponName,
            DiscountPolicy discountCode,
            Integer remainingQuantity,
            LocalDate couponStart,
            LocalDate couponEnd,
            MembershipGrade membershipCode,
            PlaceType markerCode,
            Long discountPolicyDetailId
    ) {
        this.couponName = couponName;
        this.discountCode = discountCode;
        this.remainingQuantity = remainingQuantity;
        this.couponStart = couponStart;
        this.couponEnd = couponEnd;
        this.membershipCode = membershipCode;
        this.markerCode = markerCode;
        this.discountPolicyDetailId = discountPolicyDetailId;
    }

    /**
     * 정적 생성 메서드로 객체 생성 책임을 부여
     */
    public static CouponTemplate createFrom(
            String couponName,
            Long discountPolicyDetailId,
            DiscountPolicy discountCode,
            Integer remainingQuantity,
            LocalDate couponStart,
            LocalDate couponEnd,
            PlaceType markerCode,
            MembershipGrade membershipCode
    ) {
        return CouponTemplate.builder()
                .couponName(couponName)
                .discountPolicyDetailId(discountPolicyDetailId)
                .discountCode(discountCode)
                .remainingQuantity(remainingQuantity)
                .couponStart(couponStart)
                .couponEnd(couponEnd)
                .markerCode(markerCode)
                .membershipCode(membershipCode)
                .isDeleted(false)
                .build();
    }
}
