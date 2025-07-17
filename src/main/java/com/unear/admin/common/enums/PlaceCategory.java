package com.unear.admin.common.enums;

public enum PlaceCategory {
    FOOD("FOOD", "음식"),
    LIFE("LIFE", "생활"),
    BEAUTY("BEAUTY", "뷰티"),
    HEALTH("HEALTH", "건강");

    private final String code;
    private final String description;

    PlaceCategory(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public static PlaceCategory fromCode(String code) {
        for (PlaceCategory value : values()) {
            if (value.code.equalsIgnoreCase(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid CategoryCode: " + code);
    }
}
