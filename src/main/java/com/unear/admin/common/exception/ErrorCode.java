package com.unear.admin.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS", "이메일 또는 비밀번호가 올바르지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "ACCESS_DENIED", "접근 권한이 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "DUPLICATE_EMAIL", "이미 존재하는 이메일입니다."),
    EVENT_NOT_FOUND(HttpStatus.NOT_FOUND, "EVENT_NOT_FOUND", "이벤트 정보를 찾을 수 없습니다."),
    COUPON_TEMPLATE_NOT_FOUND(HttpStatus.NOT_FOUND, "COUPON_TEMPLATE_NOT_FOUND", "쿠폰 정보를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "서버 오류가 발생했습니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST,"INVALID_REQUEST", "요청이 유효하지 않습니다."),
    PLACE_NOT_FOUND(HttpStatus.NOT_FOUND, "PLACE_NOT_FOUND", "제휴처 정보를 찾을 수 없습니다."),
    PLACE_ID_REQUIRED_FOR_UPDATE(HttpStatus.BAD_REQUEST, "PLACE_ID_REQUIRED", "placeId는 필수입니다."),
    INVALID_COUPON_POLICY(HttpStatus.BAD_REQUEST, "INVALID_COUPON_POLICY", "유효하지 않은 쿠폰 정책입니다."),
    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND, "COUPON_NOT_FOUND", "쿠폰을 찾을 수 없습니다."),
    NOT_FOUND_EVENT(HttpStatus.NOT_FOUND, "NOT_FOUND_EVENT", "이벤트를 찾을 수 없습니다."),
    NOT_FOUND_PLACE(HttpStatus.NOT_FOUND, "NOT_FOUND_PLACE", "제휴처를 찾을 수 없습니다."),
    OUT_OF_EVENT_RADIUS(HttpStatus.BAD_REQUEST,"OUT_OF_EVENT_RADIUS", "이벤트 범위를 벗어났습니다.")
    ;



    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
