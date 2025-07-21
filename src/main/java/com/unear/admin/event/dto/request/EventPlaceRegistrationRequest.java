package com.unear.admin.event.dto.request;

import com.unear.admin.places.dto.requestdto.PlaceRequestDto;
import java.util.List;

public record EventPlaceRegistrationRequest(
        PlaceRequestDto popupStore,
        List<Long> partnerPlaceIds
) {}
