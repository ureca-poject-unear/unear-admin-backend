package com.unear.admin.places.entity;

import com.unear.admin.common.enums.EventType;
import com.unear.admin.common.enums.PlaceCategory;
import com.unear.admin.common.enums.PlaceType;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
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

    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(precision = 10, scale = 7)
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

    private Long franchiseId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type_code", nullable = false)
    private EventType eventCode = EventType.NONE;

    public void updatePlaceInfo(String placeName, String placeDesc, String address,
                                String tel, BigDecimal latitude, BigDecimal longitude,
                                String benefitCategory, Integer startTime, Integer endTime,
                                PlaceCategory categoryCode, PlaceType markerCode,
                                EventType eventCode, Long franchiseId) {
        this.placeName = placeName;
        this.placeDesc = placeDesc;
        this.address = address;
        this.tel = tel;
        this.latitude = latitude;
        this.longitude = longitude;
        this.benefitCategory = benefitCategory;
        this.startTime = startTime;
        this.endTime = endTime;
        this.categoryCode = categoryCode;
        this.markerCode = markerCode;
        this.eventCode = eventCode;
        this.franchiseId = franchiseId;
    }
}
