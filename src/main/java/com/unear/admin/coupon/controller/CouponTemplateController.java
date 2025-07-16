package com.unear.admin.coupon.controller;

import com.unear.admin.common.response.ApiResponse;
import com.unear.admin.coupon.dto.CouponTemplateRequestDto;
import com.unear.admin.coupon.service.CouponTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/coupons")
@RequiredArgsConstructor
public class CouponTemplateController {

    private final CouponTemplateService couponTemplateService;

    @PostMapping("/{eventId}/coupon")
    public ResponseEntity<Void> registerCoupon(@PathVariable Long eventId,
                                               @RequestBody CouponTemplateRequestDto couponDto) {
        couponTemplateService.saveCouponTemplate(eventId, couponDto);
        return ResponseEntity.ok().build();
    }
}
