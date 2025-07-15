package com.unear.admin.auth.dto.request;

import lombok.Getter;

@Getter
public class AdminRegisterRequestDto {
    private String email;
    private String password;
    private String name;
}
