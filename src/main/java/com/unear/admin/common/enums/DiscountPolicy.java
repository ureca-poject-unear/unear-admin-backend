package com.unear.admin.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum DiscountPolicy {
    DISCOUNT("할인"),
    POINT("적립"),
    GIFT("증정"),
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
