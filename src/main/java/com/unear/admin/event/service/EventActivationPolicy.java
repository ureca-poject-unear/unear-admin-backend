package com.unear.admin.event.service;



import java.time.LocalDate;

public interface EventActivationPolicy {
    boolean shouldActivate(LocalDate startAt, LocalDate endAt);
}
