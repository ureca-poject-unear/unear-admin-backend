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

    private Long placeId;

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


    public void updateEntity(Place place) {
        place.setPlaceName(this.placeName);
        place.setPlaceDesc(this.placeDesc);
        place.setAddress(this.address);
        place.setTel(this.tel);
        place.setLatitude(this.latitude);
        place.setLongitude(this.longitude);
        place.setBenefitCategory(this.benefitCategory);
        place.setStartTime(this.startTime);
        place.setEndTime(this.endTime);
        place.setCategoryCode(this.categoryCode);
        place.setMarkerCode(this.markerCode);
        place.setEventCode(this.eventCode);
        place.setFranchiseId(this.franchiseId);
    }
}
