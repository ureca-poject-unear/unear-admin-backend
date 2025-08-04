package com.unear.admin.event.service.impl;

import com.unear.admin.coupon.repository.CouponTemplateRepository;
import com.unear.admin.event.repository.EventRepository;
import com.unear.admin.event.service.ExpiredPopupStoreCleanupService;
import com.unear.admin.eventplace.repository.EventPlaceRepository;
import com.unear.admin.places.repository.PlaceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExpiredPopupStoreCleanupServiceImpl implements ExpiredPopupStoreCleanupService {

    private final EventPlaceRepository eventPlaceRepository;
    private final PlaceRepository placeRepository;
    private final CouponTemplateRepository couponTemplateRepository;
    private final EventRepository eventRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void cleanUpExpiredPopupStores() {
        LocalDate today = LocalDate.now();

        //event 종료 처리
        eventRepository.softDeleteExpiredEvents(today);

        // 쿠폰 삭제
        couponTemplateRepository.softDeleteExpiredPopupCoupons(today);

        // places soft delete
        placeRepository.softDeleteExpiredPopupPlaces(today);

        // event_places 삭제
        eventPlaceRepository.deleteExpiredPopupEventPlaces(today);



        cleanUpExpiredGeneralPlaces(today);

        // 강제 flush + clear
        em.flush();
        em.clear();
    }

    public void cleanUpExpiredGeneralPlaces(LocalDate today) {
        // Step 1. GENERAL place → NONE
        placeRepository.updateGeneralPlacesToNone(today);

        // Step 2. 해당 event_places 삭제
        eventPlaceRepository.deleteExpiredGeneralEventPlaces(today);
    }
}
