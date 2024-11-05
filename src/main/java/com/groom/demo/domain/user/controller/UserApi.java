package com.groom.demo.domain.user.controller;

import com.groom.demo.domain.user.dto.request.LoginRequest;
import com.groom.demo.domain.user.dto.request.SignRequest;
import com.groom.demo.domain.user.dto.UserProfile;
import com.groom.demo.domain.user.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface UserApi {
    @Operation(summary = "프로필 조회 - 인증")
    public ResponseEntity<UserProfile> getMyInfo(@RequestHeader("userId") Long userId);

    @Operation(summary = "회원가입")
    public ResponseEntity<Void> signup(@RequestBody SignRequest signRequest);

    @Operation(summary = "로그인")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest);
}
