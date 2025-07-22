package com.unear.admin.coupon.controller;

import com.unear.admin.common.docs.coupontemplate.CouponTemplateApiDocs;
import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.coupon.service.CouponTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/coupons")
@RequiredArgsConstructor
public class CouponTemplateController {

    private final CouponTemplateService couponTemplateService;

//    @CouponTemplateApiDocs.PostCouponTemplate
//    @PostMapping("/{eventId}/coupon")
//    public ResponseEntity<Void> registerCoupon(@PathVariable Long eventId,
//                                               @RequestBody CouponTemplateRequestDto couponDto) {
//        couponTemplateService.saveCouponTemplate(eventId, couponDto);
//        return ResponseEntity.ok().build();
//    }
}
