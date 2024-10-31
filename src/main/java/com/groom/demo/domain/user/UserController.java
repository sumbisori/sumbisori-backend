package com.groom.demo.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my-page")
public class UserController {
    private final UserService userService;

    @GetMapping("/my-info")
    public ResponseEntity<?> getMyInfo(@RequestParam Long userId) {
        return ResponseEntity.ok(userService.getMyInfo(userId));
    }
}
