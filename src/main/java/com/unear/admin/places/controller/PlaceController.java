package com.unear.admin.places.controller;

import com.unear.admin.common.docs.event.EventDocs;
import com.unear.admin.common.docs.place.PlaceDocs;
import com.unear.admin.common.response.ApiResponse;
import com.unear.admin.places.dto.requestdto.PlaceRequestDto;
import com.unear.admin.places.dto.responsedto.PlaceResponseDto;
import com.unear.admin.places.service.PlaceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @EventDocs.PostEvent
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createPartnerPlace(
            @RequestBody @Valid PlaceRequestDto request
    ) {
        placeService.createPlace(request);
        return ResponseEntity.ok(ApiResponse.success("제휴처 등록 성공"));
    }

    @PlaceDocs.GetAllPlace
    @GetMapping
    public ResponseEntity<ApiResponse<Page<PlaceResponseDto>>> getAllPlaces(Pageable pageable) {
        Page<PlaceResponseDto> result = placeService.getAllPlaces(pageable);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PlaceDocs.UpdatePlace
    @PostMapping("/{placeId}")
    public ResponseEntity<ApiResponse<String>> updatePlace(
            @PathVariable Long placeId,
            @RequestBody @Valid PlaceRequestDto request
    ) {
        request.setPlaceId(placeId);
        placeService.updatePlace(request);
        return ResponseEntity.ok(ApiResponse.success("제휴처 수정 성공"));
    }

    @PlaceDocs.DeletePlace
    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deletePlace(@RequestParam Long placeId) {
        placeService.deletePlace(placeId);
        return ResponseEntity.ok(ApiResponse.success("제휴처 삭제 성공"));
    }

    @PlaceDocs.GetEventRadius
    @GetMapping("/{eventId}/partners")
    public ResponseEntity<ApiResponse<List<PlaceResponseDto>>> getPartnersWithinEventRadius(
            @PathVariable @Min(1) Long eventId
    ) {
        List<PlaceResponseDto> partners = placeService.getPartnersWithinEvent(eventId);
        return ResponseEntity.ok(ApiResponse.success(partners));
    }
}
