package com.unear.admin.coupon.controller;

import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.coupon.dto.response.CouponTemplateResponseDto;
import com.unear.admin.coupon.service.CouponTemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/coupons")
@RequiredArgsConstructor
public class CouponTemplateController {

    private final CouponTemplateService couponTemplateService;

    @PostMapping    //create
    public ResponseEntity<?> createGeneralCoupon(@RequestBody @Valid CouponTemplateRequestDto dto) {
        couponTemplateService.createGeneralCoupon(dto);
        return ResponseEntity.ok("일반 쿠폰 생성 완료");
    }

    @GetMapping     //read
    public ResponseEntity<List<CouponTemplateResponseDto>> getAllCoupons() {
        return ResponseEntity.ok(couponTemplateService.getAllCoupons());
    }

    @GetMapping("/{id}")    //detail read
    public ResponseEntity<CouponTemplateResponseDto> getCoupon(@PathVariable Long id) {
        return ResponseEntity.ok(couponTemplateService.getCoupon(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCoupon(@PathVariable Long id, @RequestBody @Valid CouponTemplateRequestDto dto) {
        couponTemplateService.updateCoupon(id, dto);
        return ResponseEntity.ok("쿠폰이 성공적으로 수정되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long id) {
        couponTemplateService.deleteCoupon(id);
        return ResponseEntity.ok("쿠폰이 성공적으로 삭제되었습니다.");
    }


}
