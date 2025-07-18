package com.unear.admin.common.enums;

public enum PlaceType {
    BASIC("BASIC", "기본혜택"),
    FRANCHISE("FRANCHISE", "프랜차이즈"),
    POPUP("POPUP","팝업스토어"),
    LOCAL("LOCAL", "우리동네멤버십");

    private final String code;
    private final String description;

    PlaceType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PlaceType fromCode(String code) {
        for (PlaceType value : values()) {
            if (value.code.equalsIgnoreCase(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid PlaceType code: " + code);
    }
}
