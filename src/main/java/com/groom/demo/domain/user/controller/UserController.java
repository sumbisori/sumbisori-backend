package com.groom.demo.domain.user.controller;

import com.groom.demo.domain.user.dto.UserProfile;
import com.groom.demo.domain.user.dto.request.LoginRequest;
import com.groom.demo.domain.user.dto.request.SignRequest;
import com.groom.demo.domain.user.dto.response.LoginResponse;
import com.groom.demo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements UserApi {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserProfile> getMyInfo(@RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(userService.getMyInfo(userId));
    }

    @PostMapping
    public ResponseEntity<Void> signup(@RequestBody SignRequest signRequest) {
        userService.signup(signRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.login(loginRequest));
    }
}
