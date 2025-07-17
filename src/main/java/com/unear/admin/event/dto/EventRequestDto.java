package com.unear.admin.event.dto;

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
    @NotBlank(message = "이벤트명은 필수입니다")
    private String eventName;
    @NotBlank(message = "이벤트 설명은 필수입니다")
    private String eventDescription;
    @NotNull(message = "위도는 필수입니다")
    @DecimalMin(value = "-90.0", message = "위도는 -90 이상이어야 합니다")
    @DecimalMax(value = "90.0", message = "위도는 90 이하여야 합니다")
    private BigDecimal latitude;
    @NotNull(message = "경도는 필수입니다")
    @DecimalMin(value = "-180.0", message = "경도는 -180 이상이어야 합니다")
    @DecimalMax(value = "180.0", message = "경도는 180 이하여야 합니다")
    private BigDecimal longitude;
    @NotNull(message = "반경은 필수입니다")
    @Min(value = 1, message = "반경은 1 이상이어야 합니다")
    private Integer radiusMeter;
    @NotNull(message = "시작일은 필수입니다")
    private LocalDate startAt;
    @NotNull(message = "종료일은 필수입니다")
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
