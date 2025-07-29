package com.unear.admin.coupon.repository;

import com.unear.admin.coupon.entity.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface CouponTemplateRepository extends JpaRepository<CouponTemplate, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        UPDATE CouponTemplate ct
        SET ct.isDeleted = true
        WHERE ct.couponTemplateId IN (
            SELECT e.couponTemplate.couponTemplateId
            FROM Event e
            WHERE e.endAt < :today
              AND e.couponTemplate IS NOT NULL
        )
    """)
    void softDeleteExpiredPopupCoupons(@Param("today") LocalDate today);
}
