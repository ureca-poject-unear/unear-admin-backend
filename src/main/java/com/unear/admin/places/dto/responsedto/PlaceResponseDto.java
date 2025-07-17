package com.unear.admin.places.dto.responsedto;

import com.unear.admin.places.entity.Place;

public record PlaceResponseDto(
        Long id,
        String name,
        String address,
        String tel,
        double latitude,
        double longitude
) {
    public static PlaceResponseDto from(Place place) {
        return new PlaceResponseDto(
                place.getPlaceId(),
                place.getPlaceName(),
                place.getAddress(),
                place.getTel(),
                place.getLatitude().doubleValue(),
                place.getLongitude().doubleValue()
        );
    }
}
