package com.unear.admin.places.dto.responsedto;

import com.unear.admin.places.entity.Place;

import java.math.BigDecimal;

public record PlaceResponseDto(
        Long id,
        String name,
        String address,
        String tel,
        BigDecimal latitude,
        BigDecimal longitude
) {
    public static PlaceResponseDto from(Place place) {
        return new PlaceResponseDto(
                place.getPlaceId(),
                place.getPlaceName(),
                place.getAddress(),
                place.getTel(),
                place.getLatitude() != null ? place.getLatitude() : BigDecimal.ZERO,
                place.getLongitude() != null ? place.getLongitude() : BigDecimal.ZERO
        );
    }
}
