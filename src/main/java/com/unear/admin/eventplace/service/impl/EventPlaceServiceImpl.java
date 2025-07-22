package com.unear.admin.eventplace.service.impl;

import com.unear.admin.event.entity.Event;
import com.unear.admin.event.repository.EventRepository;

import com.unear.admin.eventplace.dto.request.EventPlaceRequestDto;
import com.unear.admin.eventplace.entity.EventPlace;
import com.unear.admin.eventplace.repository.EventPlaceRepository;
import com.unear.admin.eventplace.service.EventPlaceService;
import com.unear.admin.common.exception.BusinessException;
import com.unear.admin.common.exception.ErrorCode;
import com.unear.admin.places.entity.Place;
import com.unear.admin.places.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventPlaceServiceImpl implements EventPlaceService {

    private final EventPlaceRepository eventPlaceRepository;
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;

    private static final int MAX_EVENT_PLACES = 3;

    @Override
    public void addEventPlace(List<EventPlaceRequestDto> requestList) {
        if (requestList.size() > MAX_EVENT_PLACES) {
             throw new BusinessException(ErrorCode.INVALID_REQUEST);
         }

        List<EventPlace> eventPlaces = requestList.stream()
                .map(dto -> {
                    Event event = eventRepository.findById(dto.getEventId())
                            .orElseThrow(() -> new BusinessException(ErrorCode.EVENT_NOT_FOUND));
                    Place place = placeRepository.findById(dto.getPlaceId())
                            .orElseThrow(() -> new BusinessException(ErrorCode.PLACE_NOT_FOUND));
                    return dto.toEntity(event, place);
                })
                .toList();

        eventPlaceRepository.saveAll(eventPlaces);
    }
}
