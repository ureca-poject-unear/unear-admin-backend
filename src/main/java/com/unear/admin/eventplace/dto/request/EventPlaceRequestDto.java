package com.unear.admin.eventplace.dto.request;


import com.unear.admin.event.entity.Event;
import com.unear.admin.eventplace.entity.EventPlace;
import com.unear.admin.places.entity.Place;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventPlaceRequestDto {

    private Long eventId;
    private Long placeId;
    private String eventCode;

    public EventPlace toEntity(Event event, Place place) {
        return EventPlace.builder()
                .event(event)
                .place(place)
                .eventCode(eventCode)
                .build();
    }
}
