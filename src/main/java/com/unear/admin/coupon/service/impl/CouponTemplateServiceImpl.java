package com.unear.admin.coupon.service.impl;

import com.unear.admin.common.enums.DiscountPolicy;
import com.unear.admin.coupon.dto.request.CouponTemplateRequestDto;
import com.unear.admin.coupon.dto.response.CouponTemplateResponseDto;
import com.unear.admin.coupon.entity.CouponTemplate;
import com.unear.admin.coupon.repository.CouponTemplateRepository;
import com.unear.admin.coupon.service.CouponTemplateService;
import com.unear.admin.event.entity.Event;
import com.unear.admin.event.repository.EventRepository;
import com.unear.admin.common.exception.BusinessException;
import com.unear.admin.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponTemplateServiceImpl implements CouponTemplateService {

    private final CouponTemplateRepository couponTemplateRepository;
    private final EventRepository eventRepository;

    @Override
    public void createGeneralCoupon(CouponTemplateRequestDto dto) {
        validateModifiableCoupon(dto.getDiscountCode(), dto.getDiscountPolicyId());

        CouponTemplate entity = dto.toEntity(null); // 일반 쿠폰이므로 이벤트 없음
        couponTemplateRepository.save(entity);
    }

    @Override
    public List<CouponTemplateResponseDto> getAllCoupons() {
        return couponTemplateRepository.findAll().stream()
                .map(CouponTemplateResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public CouponTemplateResponseDto getCoupon(Long id) {
        CouponTemplate coupon = couponTemplateRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));

        if (!coupon.getDiscountCode().isReadable()) {
            throw new BusinessException(ErrorCode.INVALID_COUPON_POLICY);
        }

        return CouponTemplateResponseDto.from(coupon);
    }

    @Override
    public void updateCoupon(Long id, CouponTemplateRequestDto dto) {
        CouponTemplate coupon = couponTemplateRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));

        if (!coupon.getDiscountCode().isModifiable()) {
            throw new BusinessException(ErrorCode.INVALID_COUPON_POLICY);
        }

        dto.updateEntity(coupon);
    }

    @Override
    public void deleteCoupon(Long id) {
        CouponTemplate coupon = couponTemplateRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.COUPON_NOT_FOUND));

        if (!coupon.getDiscountCode().isModifiable()) {
            throw new BusinessException(ErrorCode.INVALID_COUPON_POLICY);
        }

        couponTemplateRepository.delete(coupon);
    }

    @Override
    public void saveCouponTemplate(Long eventId, CouponTemplateRequestDto dto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EVENT_NOT_FOUND));

        couponTemplateRepository.save(dto.toEntity(event));
    }

    /**
     * 일반 쿠폰 생성 시 유효성 검사
     */
    private void validateModifiableCoupon(DiscountPolicy discountCode, Long discountPolicyId) {
        if (!discountCode.isModifiable() || discountPolicyId == null) {
            throw new BusinessException(ErrorCode.INVALID_COUPON_POLICY);
        }
    }
}
