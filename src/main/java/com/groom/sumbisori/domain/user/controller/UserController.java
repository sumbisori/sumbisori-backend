package com.groom.sumbisori.domain.user.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.common.util.CookieUtil;
import com.groom.sumbisori.domain.token.entity.TokenType;
import com.groom.sumbisori.domain.user.dto.UserProfile;
import com.groom.sumbisori.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements UserApi {
    private final UserService userService;
    private final CookieUtil cookieUtil;

    @Override
    @GetMapping
    public ResponseEntity<UserProfile> getMyInfo(@LoginUser Long userId) {
        return ResponseEntity.ok(userService.getMyInfo(userId));
    }

    @Override
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        cookieUtil.expireCookie(response, TokenType.ACCESS.name());
        return ResponseEntity.ok().build();
    }
}
