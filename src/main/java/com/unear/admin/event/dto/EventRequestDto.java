package com.unear.admin.event.dto;

import com.unear.admin.event.entity.Event;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestDto {
    private String eventName;
    private String eventDescription;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer radiusMeter;
    private LocalDate startAt;
    private LocalDate endAt;

    public Event toEntity() {
        return Event.builder()
                .eventName(eventName)
                .eventDescription(eventDescription)
                .latitude(latitude)
                .longitude(longitude)
                .radiusMeter(radiusMeter)
                .startAt(startAt)
                .endAt(endAt)
                .build();
    }
}
