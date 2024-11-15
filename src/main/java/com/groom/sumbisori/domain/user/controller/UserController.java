package com.groom.sumbisori.domain.user.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.common.util.CookieUtil;
import com.groom.sumbisori.domain.token.entity.TokenType;
import com.groom.sumbisori.domain.user.dto.UserProfile;
import com.groom.sumbisori.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController implements UserApi {
    private final UserService userService;
    private final CookieUtil cookieUtil;

    @Value("${client.url}")
    private String clientUrl;

    @Override
    @GetMapping
    public ResponseEntity<UserProfile> getMyInfo(@LoginUser Long userId) {
        return ResponseEntity.ok(userService.getMyInfo(userId));
    }

    @Override
    @GetMapping("/logout")
    public void logout(@LoginUser Long userId, HttpServletResponse response)
            throws IOException {
        if (userId != null) {
            log.info("User: {} 로그아웃", userId);
        }
        cookieUtil.expireCookie(response, TokenType.ACCESS.name());
        response.sendRedirect(clientUrl);
    }
}
