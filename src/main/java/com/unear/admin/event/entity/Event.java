package com.unear.admin.event.entity;

import com.unear.admin.coupon.entity.CouponTemplate;
import com.unear.admin.event.dto.EventRequestDto;
import com.unear.admin.places.entity.Place;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Place> places = new ArrayList<>();

    public void addPlace(Place place) {
        this.places.add(place);
        place.setEvent(this);
    }

    public void setCouponTemplate(CouponTemplate couponTemplate) {
        this.couponTemplate = couponTemplate;
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
