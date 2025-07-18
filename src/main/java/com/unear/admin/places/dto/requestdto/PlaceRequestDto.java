package com.unear.admin.places.dto.requestdto;

import com.unear.admin.common.enums.PlaceCategory;
import com.unear.admin.common.enums.PlaceType;
import com.unear.admin.event.entity.Event;
import com.unear.admin.places.entity.Place;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceRequestDto {

    private String placeName;
    private String placeDesc;
    private String address;
    private String tel;

    private BigDecimal latitude;
    private BigDecimal longitude;

    private String benefitCategory;
    private Integer startTime;
    private Integer endTime;

    private PlaceCategory categoryCode;
    private PlaceType markerCode;

    private String eventCode;
    private Long franchiseId;

    public Place toEntity(Event event) {
        return Place.builder()
                .placeName(placeName)
                .placeDesc(placeDesc)
                .address(address)
                .tel(tel)
                .latitude(latitude)
                .longitude(longitude)
                .benefitCategory(benefitCategory)
                .startTime(startTime)
                .endTime(endTime)
                .categoryCode(categoryCode)
                .markerCode(markerCode)
                .eventCode(eventCode)
                .franchiseId(franchiseId)
                .event(event)
                .build();
    }

    private int parseTime(String timeString) {
        try {
            return Integer.parseInt(timeString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 시간 값입니다: " + timeString);
        }
    }
}
