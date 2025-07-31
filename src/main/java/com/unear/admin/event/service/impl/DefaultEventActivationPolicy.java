package com.unear.admin.event.service.impl;

import com.unear.admin.event.service.EventActivationPolicy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DefaultEventActivationPolicy implements EventActivationPolicy {
    @Override
    public boolean shouldActivate(LocalDate startAt, LocalDate endAt) {
        return shouldActivate(startAt, endAt, LocalDate.now());
    }

    public boolean shouldActivate(LocalDate startAt, LocalDate endAt, LocalDate today) {
        return !startAt.isAfter(today) && !endAt.isBefore(today);
    }
}