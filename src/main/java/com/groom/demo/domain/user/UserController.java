package com.groom.demo.domain.user;

import com.groom.demo.common.config.LoginUser;
import com.groom.demo.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/my-page/my-info")
    public ResponseEntity<?> getMyInfo(@LoginUser Long userId) {
        return ResponseEntity.ok(userService.getMyInfo(userId));
    }

    @Operation(description = "회원가입")
    @PostMapping("/users")
    public ResponseEntity<?> signup(@RequestBody SignRequest signRequest) {
        userService.signup(signRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "로그인")
    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody SignRequest loginRequest) {
        Long userId = userService.login(loginRequest);
        return ResponseEntity.ok(userId);
    }
}
