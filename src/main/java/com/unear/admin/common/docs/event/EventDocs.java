package com.unear.admin.common.docs.event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.lang.annotation.*;

public class EventDocs {


    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Operation (
            summary = "이벤트 지역 등록",
            description = "이벤트 지역을 등록하고 이벤트 범위를 설정합니다."
    )
    public @interface PostEvent {
    }


    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Operation (
            summary = "이벤트 팝업스토어 등록",
            description = "이벤트 팝업스토어와 제휴처 3곳을 등록합니다."
    )
    public @interface PostPopupStore {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Operation(
            summary = "선착순 쿠폰 등록",
            description = "선착순 쿠폰을 쿠폰 템플릿을 등록합니다."
    )
    public @interface PostEventCoupon {
    }
}