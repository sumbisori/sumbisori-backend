package com.groom.sumbisori.domain.user.controller;

import com.groom.sumbisori.domain.user.dto.UserProfile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "users", description = "유저 API")
public interface UserApi {
    @Operation(summary = "프로필 조회 - 인증")
    public ResponseEntity<UserProfile> getMyInfo(Long userId);

    @Operation(summary = "로그아웃")
    public ResponseEntity<Void> logout(HttpServletResponse response);

//    @Operation(summary = "회원가입")
//    @ApiResponseExplanations(
//            errors = {
//                    @ApiExceptionExplanation(value = UserErrorCode.class, constant = NICKNAME_DUPLICATION, name = "닉네임이 중복됐을때"),
//            }
//    )
//    public ResponseEntity<Void> signup(@RequestBody SignRequest signRequest);
//
//    @Operation(summary = "로그인")
//    @ApiResponseExplanations(
//            errors = {
//                    @ApiExceptionExplanation(value = UserErrorCode.class, constant = USER_NOT_FOUND, name = "존재하지 않는 닉네임일때"),
//                    @ApiExceptionExplanation(value = UserErrorCode.class, constant = USER_NOT_MATCHED, name = "비밀번호가 일치하지 않을 때"),
//            }
//    )
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest);
}
