package com.unear.admin.coupon.controller;

import com.unear.admin.common.docs.coupontemplate.CouponTemplateApiDocs;
import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.coupon.dto.response.CouponTemplateResponseDto;
import com.unear.admin.coupon.service.CouponTemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.unear.admin.common.message.ResponseMessage.*;


@RestController
@RequestMapping("/admin/coupons")
@RequiredArgsConstructor
public class CouponTemplateController {

    private final CouponTemplateService couponTemplateService;

    @CouponTemplateApiDocs.PostCouponTemplate
    @PostMapping
    public ResponseEntity<?> createGeneralCoupon(@RequestBody @Valid CouponTemplateRequestDto dto) {
        couponTemplateService.createGeneralCoupon(dto);
        return ResponseEntity.ok(COUPON_CREATE_SUCCESS);
    }

    @CouponTemplateApiDocs.GetCouponTemplate
    @GetMapping     //read
    public ResponseEntity<List<CouponTemplateResponseDto>> getAllCoupons() {
        return ResponseEntity.ok(couponTemplateService.getAllCoupons());
    }

    @CouponTemplateApiDocs.GetCouponDetail
    @GetMapping("/{id}")    //detail read
    public ResponseEntity<CouponTemplateResponseDto> getCoupon(@PathVariable Long id) {
        return ResponseEntity.ok(couponTemplateService.getCoupon(id));
    }


    @CouponTemplateApiDocs.PutCouponDetail
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCoupon(@PathVariable Long id, @RequestBody @Valid CouponTemplateRequestDto dto) {
        couponTemplateService.updateCoupon(id, dto);
        return ResponseEntity.ok(COUPON_UPDATE_SUCCESS);
    }


    @CouponTemplateApiDocs.DeleteCoupon
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long id) {
        couponTemplateService.deleteCoupon(id);
        return ResponseEntity.ok(COUPON_DELETE_SUCCESS);
    }


}
