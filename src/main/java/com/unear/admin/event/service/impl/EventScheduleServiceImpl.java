package com.unear.admin.event.service.impl;

import com.unear.admin.event.repository.EventRepository;
import com.unear.admin.event.service.EventScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class EventScheduleServiceImpl implements EventScheduleService {

    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void activateTodayEvent() {
        LocalDate today = LocalDate.now();

        // 1. 기존 이벤트 비활성화
        eventRepository.deactivateAllEvents();

        // 2. 오늘 날짜에 맞는 이벤트 찾아 활성화
        eventRepository.findNextEventToActivate(today).ifPresent(e -> e.setIsActive(true));
    }
}