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
        PlaceResponseDto popupStore,
        List<PlaceResponseDto> partnerStores,
        List<CouponTemplateResponseDto> coupons
) {
    public static EventDetailResponseDto from(Event event, Place popupStore, List<Place> partners, List<CouponTemplate> templates) {
        return EventDetailResponseDto.builder()
                .eventId(event.getUnearEventsId())
                .eventName(event.getEventName())
                .description(event.getEventDescription())
                .latitude(event.getLatitude().doubleValue())
                .longitude(event.getLongitude().doubleValue())
                .radius(event.getRadiusMeter())
                .startDate(event.getStartAt())
                .endDate(event.getEndAt())
                .popupStore(popupStore != null ? PlaceResponseDto.from(popupStore) : null)
                .partnerStores(partners.stream().map(PlaceResponseDto::from).toList())
                .coupons(templates.stream().map(CouponTemplateResponseDto::from).toList())
                .build();
    }

}
