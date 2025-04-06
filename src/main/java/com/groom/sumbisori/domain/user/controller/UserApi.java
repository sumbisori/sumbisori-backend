package com.groom.sumbisori.domain.user.controller;

import static com.groom.sumbisori.domain.user.error.UserErrorCode.Const.KAKAO_UNLINK_FAILED;
import static com.groom.sumbisori.domain.user.error.UserErrorCode.Const.USER_NOT_FOUND;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.common.springdoc.ApiExceptionExplanation;
import com.groom.sumbisori.common.springdoc.ApiResponseExplanations;
import com.groom.sumbisori.domain.user.dto.response.UserNickname;
import com.groom.sumbisori.domain.user.dto.response.UserProfile;
import com.groom.sumbisori.domain.user.error.UserErrorCode;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "users", description = "유저 API")
public interface UserApi {
    @Operation(summary = "프로필 조회")
    ResponseEntity<UserProfile> getMyInfo(Long userId);

    @Operation(summary = "로그인한 유저의 닉네임 조회")
    ResponseEntity<UserNickname> getMyNickname(@LoginUser Long userId);

    @Operation(summary = "로그아웃")
    @Hidden
    void logout(@LoginUser Long userId, HttpServletResponse response) throws Exception;

    @Operation(summary = "회원탈퇴")
    @ApiResponseExplanations(
            errors = {
                    @ApiExceptionExplanation(value = UserErrorCode.class, constant = USER_NOT_FOUND, name = "사용자를 찾을 수 없을 때"),
                    @ApiExceptionExplanation(value = UserErrorCode.class, constant = KAKAO_UNLINK_FAILED, name = "카카오 연결 끊기에 실패했을 때")
            }
    )
    ResponseEntity<Void> deleteUser(@LoginUser Long userId, HttpServletResponse response);
}
