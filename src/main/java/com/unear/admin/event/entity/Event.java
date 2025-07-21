package com.unear.admin.event.entity;

import com.unear.admin.coupon.entity.CouponTemplate;
import com.unear.admin.event.dto.request.EventRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
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

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private CouponTemplate couponTemplate;

    public void setCouponTemplate(CouponTemplate couponTemplate) {
        this.couponTemplate = couponTemplate;
        if (couponTemplate != null) {
            couponTemplate.setEvent(this);
        }
    }

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
}
