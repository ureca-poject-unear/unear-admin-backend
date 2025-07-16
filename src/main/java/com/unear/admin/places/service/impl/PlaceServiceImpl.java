package com.unear.admin.places.service.impl;

import com.unear.admin.event.entity.Event;
import com.unear.admin.event.repository.EventRepository;
import com.unear.admin.exception.BusinessException;
import com.unear.admin.exception.ErrorCode;
import com.unear.admin.places.dto.PlaceRequestDto;
import com.unear.admin.places.entity.Place;
import com.unear.admin.places.repository.PlaceRepository;
import com.unear.admin.places.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final EventRepository eventRepository;

    @Override
    public void savePlace(Long eventId, PlaceRequestDto dto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EVENT_NOT_FOUND));

        Place place = dto.toEntity(event); // 변환 책임은 DTO 내부

        placeRepository.save(place);
    }
}
