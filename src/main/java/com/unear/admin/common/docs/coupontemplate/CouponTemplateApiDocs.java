package com.unear.admin.common.docs.coupontemplate;

import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.coupon.dto.response.CouponTemplateResponseDto;
import com.unear.admin.places.dto.responsedto.PlaceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import java.lang.annotation.*;

public class CouponTemplateApiDocs {
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Operation(
            summary = "쿠폰 등록",
            description = "선착순 쿠폰을 제외한 나머지 일반 쿠폰 등록입니다."
    )
    public @interface PostCouponTemplate {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Operation(
            summary = "쿠폰 목록 조회",
            description = "쿠폰 템플릿에있는 쿠폰들을 모두 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "쿠폰 목록 조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CouponTemplateResponseDto.class),
                    examples = {
                            @ExampleObject(
                                    name = "쿠폰 목록 조회",
                                    summary = "쿠폰 템플릿에 있는 쿠폰 목록 조회",
                                    value = """
                                            [
                                                {
                                                    "couponId": 5,
                                                    "couponName": "GS THE FRESH",
                                                    "discountPolicy": "COUPON_FIXED",
                                                    "discountPolicyLabel": "(쿠폰) 금액 할인",
                                                    "remainingQuantity": -1,
                                                    "couponStart": "2025-07-01",
                                                    "couponEnd": "2025-07-30"
                                                },
                                                {
                                                    "couponId": 4,
                                                    "couponName": "GS THE FRESH",
                                                    "discountPolicy": "COUPON_FIXED",
                                                    "discountPolicyLabel": "(쿠폰) 금액 할인",
                                                    "remainingQuantity": -1,
                                                    "couponStart": "2025-07-01",
                                                    "couponEnd": "2025-07-30"
                                                },
                                                {
                                                    "couponId": 6,
                                                    "couponName": "GS THE FRESH",
                                                    "discountPolicy": "COUPON_FIXED",
                                                    "discountPolicyLabel": "(쿠폰) 금액 할인",
                                                    "remainingQuantity": -1,
                                                    "couponStart": "2025-07-01",
                                                    "couponEnd": "2025-07-30"
                                                },
                                                {
                                                    "couponId": 41,
                                                    "couponName": "팝업 선착순 할인 쿠폰",
                                                    "discountPolicy": "COUPON_FCFS",
                                                    "discountPolicyLabel": "선착순 전용 쿠폰",
                                                    "remainingQuantity": 100,
                                                    "couponStart": "2025-08-01",
                                                    "couponEnd": "2025-08-31"
                                                },
                                                {
                                                    "couponId": 42,
                                                    "couponName": "VIP 3000원 할인 쿠폰",
                                                    "discountPolicy": "COUPON_FIXED",
                                                    "discountPolicyLabel": "(쿠폰) 금액 할인",
                                                    "remainingQuantity": 100,
                                                    "couponStart": "2025-08-01",
                                                    "couponEnd": "2025-08-31"
                                                },
                                                {
                                                    "couponId": 43,
                                                    "couponName": "VIP 3000원 할인 쿠폰",
                                                    "discountPolicy": "COUPON_FIXED",
                                                    "discountPolicyLabel": "(쿠폰) 금액 할인",
                                                    "remainingQuantity": -1,
                                                    "couponStart": "2025-08-01",
                                                    "couponEnd": "2025-08-31"
                                                },
                                                {
                                                    "couponId": 2,
                                                    "couponName": "서울랜드",
                                                    "discountPolicy": "COUPON_PERCENT",
                                                    "discountPolicyLabel": "(쿠폰) 퍼센트 할인",
                                                    "remainingQuantity": -1,
                                                    "couponStart": "2025-07-01",
                                                    "couponEnd": "2025-07-30"
                                                },
                                                {
                                                    "couponId": 3,
                                                    "couponName": "대관령 하늘 목장",
                                                    "discountPolicy": "COUPON_FIXED",
                                                    "discountPolicyLabel": "(쿠폰) 금액 할인",
                                                    "remainingQuantity": -1,
                                                    "couponStart": "2025-07-01",
                                                    "couponEnd": "2025-07-30"
                                                }
                                            ]
                                            """
                            )
                    }
            )
    )
    public @interface GetCouponTemplate {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Operation(
            summary = "쿠폰 목록 조회",
            description = "쿠폰 템플릿에있는 쿠폰들을 모두 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "쿠폰 상세 조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CouponTemplateResponseDto.class),
                    examples = {
                            @ExampleObject(
                                    name = "쿠폰 상세 조회",
                                    summary = "쿠폰 템플릿에 있는 쿠폰 상세 조회",
                                    value = """
                                            {
                                                "couponId": 2,
                                                "couponName": "서울랜드",
                                                "discountPolicy": "COUPON_PERCENT",
                                                "discountPolicyLabel": "(쿠폰) 퍼센트 할인",
                                                "remainingQuantity": -1,
                                                "couponStart": "2025-07-01",
                                                "couponEnd": "2025-07-30"
                                            }
                                            """
                            )
                    }
            )
    )
    public @interface GetCouponDetail {}

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Operation(
            summary = "쿠폰 수정",
            description = "쿠폰 정보를 수정합니다."
    )
    public @interface PutCouponDetail {
    }


    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Operation(
            summary = "쿠폰 삭제",
            description = "쿠폰을 삭제합니다."
    )
    public @interface DeleteCoupon {
    }
}
