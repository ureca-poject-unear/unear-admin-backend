package com.unear.admin.event.scheduler;

import com.unear.admin.event.service.EventScheduleService;
import com.unear.admin.event.service.ExpiredPopupStoreCleanupService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ExpiredPlaceScheduler {

    private final ExpiredPopupStoreCleanupService expiredPopupStoreCleanupService;
    private final EventScheduleService eventScheduleService;

    @Transactional
//    @Scheduled(cron = "0 0 0 * * *")
//    public void runCleanupTask() {
//        expiredPopupStoreCleanupService.cleanUpExpiredPopupStores(); // ← 아래 순서로 위임
//        eventScheduleService.activateTodayEvent();
//    }

    @Scheduled(fixedDelay = 100000) // 10초마다 실행
    public void testRunCleanupTask() {
        System.out.println("테스트 실행됨");
        expiredPopupStoreCleanupService.cleanUpExpiredPopupStores();
        eventScheduleService.activateTodayEvent();
    }
}