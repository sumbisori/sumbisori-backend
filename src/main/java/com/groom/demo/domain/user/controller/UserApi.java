package com.groom.demo.domain.user.controller;

import static com.groom.demo.domain.user.error.UserErrorCode.Const.NICKNAME_DUPLICATION;
import static com.groom.demo.domain.user.error.UserErrorCode.Const.USER_NOT_FOUND;
import static com.groom.demo.domain.user.error.UserErrorCode.Const.USER_NOT_MATCHED;

import com.groom.demo.common.springdoc.ApiExceptionExplanation;
import com.groom.demo.common.springdoc.ApiResponseExplanations;
import com.groom.demo.domain.user.dto.request.LoginRequest;
import com.groom.demo.domain.user.dto.request.SignRequest;
import com.groom.demo.domain.user.dto.UserProfile;
import com.groom.demo.domain.user.dto.response.LoginResponse;
import com.groom.demo.domain.user.error.UserErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "users", description = "유저 API")
public interface UserApi {
    @Operation(summary = "프로필 조회 - 인증")
    public ResponseEntity<UserProfile> getMyInfo(@RequestHeader("userId") Long userId);

    @Operation(summary = "회원가입")
    @ApiResponseExplanations(
            errors = {
                    @ApiExceptionExplanation(value = UserErrorCode.class, constant = NICKNAME_DUPLICATION, name = "닉네임이 중복됐을때"),
            }
    )
    public ResponseEntity<Void> signup(@RequestBody SignRequest signRequest);

    @Operation(summary = "로그인")
    @ApiResponseExplanations(
            errors = {
                    @ApiExceptionExplanation(value = UserErrorCode.class, constant = USER_NOT_FOUND, name = "존재하지 않는 닉네임일때"),
                    @ApiExceptionExplanation(value = UserErrorCode.class, constant = USER_NOT_MATCHED, name = "비밀번호가 일치하지 않을 때"),
            }
    )
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest);
}
