package com.unear.admin.event.dto.request;

import com.unear.admin.common.message.ResponseMessage;
import com.unear.admin.event.entity.Event;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestDto {
    @NotNull
    private String eventName;

    @NotNull
    private String eventDescription;

    @NotNull
    @DecimalMin(value = "-90.0", message = ResponseMessage.LATITUDE_MIN)
    @DecimalMax(value = "90.0", message = ResponseMessage.LATITUDE_MAX)
    private BigDecimal latitude;

    @NotNull
    @DecimalMin(value = "-180.0", message = ResponseMessage.LONGITUDE_MIN)
    @DecimalMax(value = "180.0", message =  ResponseMessage.LONGITUDE_MAX)
    private BigDecimal longitude;

    @NotNull
    @Min(value = 1, message = ResponseMessage.RADIUS_MIN)
    private Integer radiusMeter;

    @NotNull
    private LocalDate startAt;

    @NotNull
    private LocalDate endAt;

    public Event toEntity(boolean isActive) {
        return Event.builder()
                .eventName(eventName)
                .eventDescription(eventDescription)
                .latitude(latitude)
                .longitude(longitude)
                .radiusMeter(radiusMeter)
                .startAt(startAt)
                .endAt(endAt)
                .isActive(isActive)
                .build();
    }
}
