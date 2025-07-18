package com.unear.admin.places.service;

import com.unear.admin.places.dto.requestdto.PlaceRequestDto;
import com.unear.admin.places.dto.responsedto.PlaceResponseDto;

import java.util.List;

public interface PlaceService {
    void savePlace(Long eventId, PlaceRequestDto requestDto);
    List<PlaceResponseDto> getPartnersWithinEvent(Long eventId);
}
