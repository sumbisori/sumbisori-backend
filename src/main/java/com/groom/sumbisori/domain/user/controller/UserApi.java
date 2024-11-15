package com.groom.sumbisori.domain.user.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.domain.user.dto.UserProfile;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "users", description = "유저 API")
public interface UserApi {
    @Operation(summary = "프로필 조회 - 인증")
    ResponseEntity<UserProfile> getMyInfo(Long userId);

    @Operation(summary = "로그아웃")
    @Hidden
    void logout(@LoginUser Long userId, HttpServletResponse response) throws Exception;
}
