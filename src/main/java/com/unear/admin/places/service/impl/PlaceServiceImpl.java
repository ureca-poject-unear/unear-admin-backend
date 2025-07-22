package com.unear.admin.places.service.impl;

import com.unear.admin.common.enums.EventType;
import com.unear.admin.event.entity.Event;
import com.unear.admin.event.repository.EventRepository;
import com.unear.admin.eventplace.entity.EventPlace;
import com.unear.admin.eventplace.repository.EventPlaceRepository;
import com.unear.admin.common.exception.BusinessException;
import com.unear.admin.common.exception.ErrorCode;
import com.unear.admin.places.dto.requestdto.PlaceRequestDto;
import com.unear.admin.places.dto.responsedto.PlaceResponseDto;
import com.unear.admin.places.entity.Place;
import com.unear.admin.places.repository.PlaceRepository;
import com.unear.admin.places.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final EventRepository eventRepository;
    private final EventPlaceRepository eventPlaceRepository;

    @Override
    public void createPlace(PlaceRequestDto requestDto) {
        Place place = requestDto.toEntity(); // 일반 제휴처 등록 (eventCode = NONE)
        placeRepository.save(place);
    }

    @Override
    public Page<PlaceResponseDto> getAllPlaces(Pageable pageable) {
        return placeRepository.findAll(pageable)
                .map(PlaceResponseDto::from);
    }

    @Override
    public void deletePlace(Long placeId) {
        if (!placeRepository.existsById(placeId)) {
            throw new BusinessException(ErrorCode.PLACE_NOT_FOUND);
        }
        placeRepository.deleteById(placeId);
    }

    @Override
    public void updatePlace(PlaceRequestDto dto) {
        Long placeId = dto.getPlaceId();
        if (placeId == null) {
            throw new BusinessException(ErrorCode.PLACE_ID_REQUIRED_FOR_UPDATE);
        }

        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PLACE_NOT_FOUND));

        dto.updateEntity(place);
    }

    @Override
    public void savePlace(Long eventId, PlaceRequestDto dto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EVENT_NOT_FOUND));

        // 팝업스토어는 독립적인 매장으로 프랜차이즈에 속하지 않음
        Place place = dto.toEntity();
        if (dto.getEventCode() == EventType.REQUIRE) {
            place.setFranchiseId(null);
        }

        // 이벤트 연관 매장이라면 event_places 등록
        if (place.getEventCode() != EventType.NONE) {
            EventPlace mapping = EventPlace.builder()
                    .event(event)
                    .place(place)
                    .eventCode(place.getEventCode())
                    .build();
            eventPlaceRepository.save(mapping);
        }
    }

    @Override
    public List<PlaceResponseDto> getPartnersWithinEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new BusinessException(ErrorCode.EVENT_NOT_FOUND);
        }

        List<Place> result = placeRepository.findPartnersWithinEventRadius(eventId);
        return result.stream()
                .map(PlaceResponseDto::from)
                .toList();
    }
}
