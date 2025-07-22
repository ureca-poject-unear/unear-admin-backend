package com.unear.admin.event.dto.request;

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
    @DecimalMin(value = "-90.0", message = "위도는 -90 이상이어야 합니다")
    @DecimalMax(value = "90.0", message = "위도는 90 이하여야 합니다")
    private BigDecimal latitude;

    @NotNull
    @DecimalMin(value = "-180.0", message = "경도는 -180 이상이어야 합니다")
    @DecimalMax(value = "180.0", message = "경도는 180 이하여야 합니다")
    private BigDecimal longitude;

    @NotNull
    @Min(value = 1, message = "반경은 1 이상이어야 합니다")
    private Integer radiusMeter;

    @NotNull
    private LocalDate startAt;

    @NotNull
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
