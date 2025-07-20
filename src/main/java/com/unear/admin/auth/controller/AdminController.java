package com.unear.admin.auth.controller;

import com.unear.admin.auth.dto.request.AdminRegisterRequestDto;
import com.unear.admin.auth.dto.request.LoginRequestDto;
import com.unear.admin.auth.dto.response.LoginResponseDto;
import com.unear.admin.auth.entity.Admin;
import com.unear.admin.auth.repository.AdminRepository;
import com.unear.admin.common.response.ApiResponse;
import com.unear.admin.common.security.CustomAdminDetails;
import com.unear.admin.exception.BusinessException;
import com.unear.admin.exception.ErrorCode;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AdminController {

    private final AuthenticationManager authenticationManager;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> registerAdmin(@RequestBody AdminRegisterRequestDto request) {
        // 이미 존재하는 관리자 방지
        if (adminRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL); // enum에 추가해줘야 함
        }

        // 비밀번호 해싱
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 관리자 저장
        Admin admin = Admin.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .name(request.getName())
                .role("ROLE_ADMIN")
                .build();

        adminRepository.save(admin);

        return ResponseEntity.ok(ApiResponse.success("관리자 등록 성공", null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto request, HttpSession session) {

        try {
            // 인증 시도 (Spring Security 내부적으로 DB 조회 + 비밀번호 검증 수행)
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            // 인증된 사용자 정보 추출
            CustomAdminDetails adminDetails = (CustomAdminDetails) authentication.getPrincipal();
            Admin admin = adminDetails.getAdmin();

            // 세션에 로그인된 관리자 정보 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            LoginResponseDto response = new LoginResponseDto(
                    admin.getAdminId(),
                    admin.getEmail(),
                    admin.getName(),
                    admin.getRole()
            );

            return ResponseEntity.ok(ApiResponse.success("로그인 성공", response));

        } catch (AuthenticationException e) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
        session.invalidate(); // 세션 제거
        return ResponseEntity.ok(ApiResponse.success("로그아웃 완료", null));
    }
}



