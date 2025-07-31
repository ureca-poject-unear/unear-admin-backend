package com.unear.admin.event.entity;

import com.unear.admin.common.util.LocationUtils;
import com.unear.admin.coupon.entity.CouponTemplate;
import com.unear.admin.event.dto.request.EventRequestDto;
import com.unear.admin.places.entity.Place;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "unear_events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unear_event_id")
    private Long unearEventsId;

    private String eventName;
    private String eventDescription;

    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(precision = 10, scale = 7)
    private BigDecimal longitude;

    private Integer radiusMeter;
    private LocalDate startAt;
    private LocalDate endAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_template_id")
    private CouponTemplate couponTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "popup_store_id")
    private Place popupStore;

    @Column(name = "isActive")
    private Boolean isActive;


    public static Event fromDto(EventRequestDto dto) {
        return Event.builder()
                .eventName(dto.getEventName())
                .eventDescription(dto.getEventDescription())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .radiusMeter(dto.getRadiusMeter())
                .startAt(dto.getStartAt())
                .endAt(dto.getEndAt())
                .build();
    }

    public boolean isWithinRadius(Place place) {
        double distance = LocationUtils.calculateDistance(
                this.latitude.doubleValue(), this.longitude.doubleValue(),
                place.getLatitude().doubleValue(), place.getLongitude().doubleValue()
        );
        return distance <= this.radiusMeter;
    }

    public void assignCoupon(CouponTemplate coupon) {
        this.couponTemplate = coupon;
    }
}
