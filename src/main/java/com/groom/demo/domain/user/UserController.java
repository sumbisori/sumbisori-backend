package com.groom.demo.domain.user;

import com.groom.demo.common.config.LoginUser;
import com.groom.demo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class UserController {
    private final UserService userService;

    @GetMapping("/my-info")
    public ResponseEntity<?> getMyInfo(@LoginUser Long userId) {
        return ResponseEntity.ok(userService.getMyInfo(userId));
    }
}
