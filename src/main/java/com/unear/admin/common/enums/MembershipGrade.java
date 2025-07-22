package com.unear.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MembershipGrade {

    BASIC("BASIC", "우수"),
    VIP("VIP", "VIP"),
    VVIP("VVIP", "VVIP"),
    ALL("ALL", "모든등급");

    private final String code;        // 공통코드 id
    private final String displayName; // 공통코드 이름

    public static MembershipGrade fromCode(String code) {
        for (MembershipGrade grade : values()) {
            if (grade.code.equalsIgnoreCase(code)) {
                return grade;
            }
        }
        throw new IllegalArgumentException("Unknown membership grade code: " + code);
    }
}
