package com.unear.admin.places.entity;

import com.unear.admin.common.enums.PlaceCategory;
import com.unear.admin.common.enums.PlaceType;
import com.unear.admin.event.entity.Event;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    private String placeName;
    private String placeDesc;
    private String address;
    private String tel;

    private BigDecimal latitude;
    private BigDecimal longitude;

    @Column(name = "benefit_category")
    private String benefitCategory;

    private Integer startTime;
    private Integer endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_code")
    private PlaceCategory categoryCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "marker_code")
    private PlaceType markerCode;

    private String eventCode;
    private Long franchiseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unear_event_id")
    private Event event;

    public void setEvent(Event event) {
        this.event = event;
    }
}
