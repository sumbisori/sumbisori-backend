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

    @Override
    @GetMapping
    public ResponseEntity<UserProfile> getMyInfo(@LoginUser Long userId) {
        return ResponseEntity.ok(userService.getMyInfo(userId));
    }

    @Override
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        CookieUtil.expireCookie(response, TokenType.ACCESS.name());
        return ResponseEntity.ok().build();
    }

    //    @PostMapping
//    public ResponseEntity<Void> signup(@RequestBody SignRequest signRequest) {
//        userService.signup(signRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(userService.login(loginRequest));
//    }
}
