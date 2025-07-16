package com.unear.admin.common.enums;

public enum PlaceType {
    GENERAL("GENERAL", "일반 마커"),
    FRANCHISE("FRANCHISE", "프랜차이즈 마커"),
    POPUP("POPUP","팝업스토어");

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
        throw new IllegalArgumentException("Invalid MarkerCode: " + code);
    }
}
