package com.unear.admin.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum DiscountPolicy {
    COUPON_FIXED("(쿠폰) 금액 할인"),
    COUPON_PERCENT("(쿠폰) 퍼센트 할인"),
    MEMBERSHIP_UNIT("(멤버십) 금액당 할인"),
    MEMBERSHIP_FIXED("(멤버십) 금액 할인"),
    COUPON_FCFS("선착순 전용 쿠폰");

    private final String label;

    DiscountPolicy(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static DiscountPolicy from(String value) {
        return Arrays.stream(DiscountPolicy.values())
                .filter(policy -> policy.name().equalsIgnoreCase(value) || policy.label.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid DiscountPolicy: " + value));
    }
}
