package com.unear.admin.coupon.entity;

import com.unear.admin.event.entity.Event;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "coupon_templates")
public class CouponTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponTemplateId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unear_event_id")
    private Event event;

    private String couponName;
    private String description;

    private String discountPolicy;
    private Integer remainingQuantity;

    private LocalDate couponStart;
    private LocalDate couponEnd;

    public void setEvent(Event event) {
        this.event = event;
    }
}
