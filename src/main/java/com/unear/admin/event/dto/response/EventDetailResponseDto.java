package com.unear.admin.event.dto.response;

import com.unear.admin.coupon.dto.response.CouponTemplateResponseDto;
import com.unear.admin.coupon.entity.CouponTemplate;
import com.unear.admin.event.entity.Event;
import com.unear.admin.places.dto.responsedto.PlaceResponseDto;
import com.unear.admin.places.entity.Place;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record EventDetailResponseDto(
        Long eventId,
        String eventName,
        String description,
        double latitude,
        double longitude,
        int radius,
        LocalDate startDate,
        LocalDate endDate,
        List<PlaceResponseDto> partnerStores,
        List<CouponTemplateResponseDto> coupons
) {
    public static EventDetailResponseDto from(Event event) {
        return EventDetailResponseDto.builder()
                .eventId(event.getUnearEventsId())
                .eventName(event.getEventName())
                .description(event.getEventDescription())
                .latitude(event.getLatitude().doubleValue())
                .longitude(event.getLongitude().doubleValue())
                .radius(event.getRadiusMeter())
                .startDate(event.getStartAt())
                .endDate(event.getEndAt())
                .partnerStores(List.of()) // or null, 필요에 따라 조절
                .coupons(List.of())       // or null, 필요에 따라 조절
                .build();
    }

}
