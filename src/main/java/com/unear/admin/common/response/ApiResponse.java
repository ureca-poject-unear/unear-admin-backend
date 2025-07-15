package com.unear.admin.common.response;

import com.unear.admin.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private int resultCode;
    private String codeName;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), "SUCCESS", message, data);
    }

    public static <T> ApiResponse<T> success(T data) {
        return success("요청이 성공적으로 처리되었습니다.", data);
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<>(
                errorCode.getStatus().value(),
                errorCode.getCode(),
                errorCode.getMessage(),
                null
        );
    }
}
