package com.unear.admin.eventplace.entity;

import com.unear.admin.common.enums.EventType;
import com.unear.admin.common.enums.PlaceCategory;
import com.unear.admin.event.entity.Event;
import com.unear.admin.places.entity.Place;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "event_places")
public class EventPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_place_id")
    private Long eventPlaceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unear_event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_code")
    private EventType eventCode;
}
