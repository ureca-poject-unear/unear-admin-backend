package com.unear.admin.eventplace.controller;

import com.unear.admin.eventplace.dto.request.EventPlaceRequestDto;
import com.unear.admin.eventplace.service.EventPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/event-places")
@RequiredArgsConstructor
public class EventPlaceController {

    private final EventPlaceService eventPlaceService;

    @PostMapping
    public ResponseEntity<Void> registerEventPlaces(@RequestBody List<EventPlaceRequestDto> requestList) {
        eventPlaceService.addEventPlace(requestList);
        return ResponseEntity.ok().build();
    }
}
