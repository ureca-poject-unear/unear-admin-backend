package com.unear.admin.places.service.impl;

import com.unear.admin.event.entity.Event;
import com.unear.admin.event.repository.EventRepository;
import com.unear.admin.exception.BusinessException;
import com.unear.admin.exception.ErrorCode;
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

    @Override   // crud
    public void createPlace(PlaceRequestDto requestDto) {
        Place place = requestDto.toEntity(null); // 이벤트 없이 제휴처만 등록
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

        place.updatePlaceInfo(
                dto.getPlaceName(),
                dto.getPlaceDesc(),
                dto.getAddress(),
                dto.getTel(),
                dto.getLatitude(),
                dto.getLongitude(),
                dto.getBenefitCategory(),
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getCategoryCode(),
                dto.getMarkerCode(),
                dto.getEventCode(),
                dto.getFranchiseId()
        );
    }


    @Override
    public void savePlace(Long eventId, PlaceRequestDto dto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EVENT_NOT_FOUND));

        Place place = dto.toEntity(event);

        placeRepository.save(place);
    }

    @Override
    public List<PlaceResponseDto> getPartnersWithinEvent(Long eventId) {
        eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EVENT_NOT_FOUND));
        
        List<Place> result = placeRepository.findPartnersWithinEventRadius(eventId);
        return result.stream()
                .map(PlaceResponseDto::from)
                .toList();
    }
}
