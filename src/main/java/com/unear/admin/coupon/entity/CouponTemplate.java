package com.unear.admin.coupon.entity;

import com.unear.admin.common.enums.DiscountPolicy;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_code")
    private DiscountPolicy discountPolicy;

    private Integer remainingQuantity;

    private LocalDate couponStart;
    private LocalDate couponEnd;

    @Column(name = "marker_code")
    private String markerCode;

    @Column(name = "membership_code")
    private String membershipCode;

    public void setEvent(Event event) {
        this.event = event;
    }
}
