package com.unear.admin.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private Long adminId;
    private String email;
    private String name;
    private String Role;
}