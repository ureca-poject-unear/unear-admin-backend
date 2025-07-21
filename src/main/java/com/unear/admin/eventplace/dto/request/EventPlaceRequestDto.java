package com.unear.admin.eventplace.dto.request;


import com.unear.admin.common.enums.EventType;
import com.unear.admin.common.enums.PlaceCategory;
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
    private EventType eventCode;

    public EventPlace toEntity(Event event, Place place) {
        return EventPlace.builder()
                .event(event)
                .place(place)
                .eventCode(eventCode)
                .build();
    }
}
