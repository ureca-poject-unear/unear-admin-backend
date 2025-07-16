package com.unear.admin.places.service;

import com.unear.admin.places.dto.PlaceRequestDto;

public interface PlaceService {
    void savePlace(Long eventId, PlaceRequestDto requestDto);
}
