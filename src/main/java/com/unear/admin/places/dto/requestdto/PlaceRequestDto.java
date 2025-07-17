package com.unear.admin.places.dto.requestdto;

import com.unear.admin.common.enums.PlaceCategory;
import com.unear.admin.common.enums.PlaceType;
import com.unear.admin.common.enums.DiscountPolicy;
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
    private String placeDescription;
    private String address;
    private String tel;

    private BigDecimal latitude;
    private BigDecimal longitude;

    private String startTime; // UI 입력값
    private String endTime;

    private PlaceType markerCode;
    private PlaceCategory categoryCode;
    private DiscountPolicy benefitCategory;

    private Long franchiseId;

    public Place toEntity(Event event) {
        return Place.builder()
                .placeName(placeName)
                .placeDesc(placeDescription)
                .address(address)
                .tel(tel)
                .latitude(latitude)
                .longitude(longitude)
                .startTime(parseTime(startTime))
                .endTime(parseTime(endTime))
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
