package com.unear.admin.eventplace.service;

import com.unear.admin.eventplace.dto.request.EventPlaceRequestDto;

import java.util.List;

public interface EventPlaceService {

    void addEventPlace(List<EventPlaceRequestDto> requestList);
}
