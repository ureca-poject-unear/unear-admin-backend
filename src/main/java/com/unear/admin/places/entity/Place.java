package com.unear.admin.places.entity;

import com.unear.admin.event.entity.Event;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unear_event_id")
    private Event event;

    private String placeName;
    private String placeDesc;
    private String address;

    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(precision = 10, scale = 7)
    private BigDecimal longitude;


    private Integer startTime;
    private Integer endTime;

    private String tel;


    public void setEvent(Event event) {
        this.event = event;
    }
}
