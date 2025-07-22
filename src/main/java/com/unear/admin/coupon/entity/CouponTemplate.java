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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unear_event_id")
    private Event event;

    private String couponName;
    private Long discountPolicyDetailId;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_code")
    private DiscountPolicy discountCode;

    private Integer remainingQuantity;

    private LocalDate couponStart;
    private LocalDate couponEnd;

    @Enumerated(EnumType.STRING)
    @Column(name = "marker_code")
    private PlaceType markerCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_code")
    private MembershipGrade membershipCode;

    public void setEvent(Event event) {
        this.event = event;
    }

    public void update(String couponName, DiscountPolicy discountCode, Integer remainingQuantity,
                       LocalDate couponStart, LocalDate couponEnd,
                       MembershipGrade membershipCode, PlaceType markerCode, Long discountPolicyDetailId) {

        this.couponName = couponName;
        this.discountCode = discountCode;
        this.remainingQuantity = remainingQuantity;
        this.couponStart = couponStart;
        this.couponEnd = couponEnd;
        this.membershipCode = membershipCode;
        this.markerCode = markerCode;
        this.discountPolicyDetailId = discountPolicyDetailId;
    }
}
