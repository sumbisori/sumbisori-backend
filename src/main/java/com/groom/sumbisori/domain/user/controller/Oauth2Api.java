package com.groom.sumbisori.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Tag(name = "oauth2", description = "Oauth2 API")
public interface Oauth2Api {
    @Operation(summary = "로그아웃 with kakao")
    @ApiResponse(responseCode = "301", description = "Redirect to kakao logout url")
    void logout(HttpServletResponse response) throws IOException;
}
