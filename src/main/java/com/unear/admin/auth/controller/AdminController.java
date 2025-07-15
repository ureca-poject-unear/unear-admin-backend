package com.unear.admin.auth.controller;

import com.unear.admin.auth.dto.response.LoginResponseDto;
import com.unear.admin.auth.entity.Admin;
import com.unear.admin.auth.repository.AdminRepository;
import com.unear.admin.auth.dto.request.LoginRequestDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AdminController {
    private final AdminRepository adminRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request, HttpSession session) {
         // 입력 유효성 검사 추가
        if (request.getEmail() == null || request.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");
        }
        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "존재하지 않는 관리자 입니다."));

        if (!admin.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }
        session.setAttribute("LOGIN_ADMIN", admin.getAdminId());

        LoginResponseDto loginResponseDto = new LoginResponseDto(
                admin.getAdminId(),
                admin.getEmail(),
                admin.getName()
        );

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃");
    }
}



