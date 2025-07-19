package com.unear.admin.places.controller;

import com.unear.admin.common.response.ApiResponse;
import com.unear.admin.places.dto.requestdto.PlaceRequestDto;
import com.unear.admin.places.dto.responsedto.PlaceResponseDto;
import com.unear.admin.places.service.PlaceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;


    @PostMapping
    public ResponseEntity<ApiResponse<String>> createPartnerPlace(
            @RequestBody @Valid PlaceRequestDto request
    ) {
        placeService.createPlace(request);
        return ResponseEntity.ok(ApiResponse.success("제휴처 등록 성공"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PlaceResponseDto>>> getAllPlaces() {
        List<PlaceResponseDto> result = placeService.getAllPlaces();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<String>> updatePlace(@RequestBody @Valid PlaceRequestDto request) {
        placeService.updatePlace(request);
        return ResponseEntity.ok(ApiResponse.success("제휴처 수정 성공"));
    }


    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deletePlace(@RequestParam Long placeId) {
        placeService.deletePlace(placeId);
        return ResponseEntity.ok(ApiResponse.success("제휴처 삭제 성공"));
    }

    @PostMapping("/{eventId}/popup")
    public ResponseEntity<ApiResponse<String>> registerPopupPlace(
            @PathVariable Long eventId,
            @RequestBody @Valid PlaceRequestDto request
    ) {
        placeService.savePlace(eventId, request);
        return ResponseEntity.ok(ApiResponse.success("팝업스토어 등록 성공"));
    }

    @GetMapping("/{eventId}/partners")
    public ResponseEntity<ApiResponse<List<PlaceResponseDto>>> getPartnersWithinEventRadius(
            @PathVariable @Min(1) Long eventId
    ) {
        List<PlaceResponseDto> partners = placeService.getPartnersWithinEvent(eventId);
        return ResponseEntity.ok(ApiResponse.success(partners));
    }
}
