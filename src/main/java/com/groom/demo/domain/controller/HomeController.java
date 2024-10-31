package com.groom.demo.domain.controller;

import com.groom.demo.domain.seafood.SeafoodService;
import com.groom.demo.domain.user.SignRequest;
import com.groom.demo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/home")
public class HomeController {
    private final SeafoodService seafoodService;

    @GetMapping("/seafoods")
    public ResponseEntity<?> getMySeafoods(@RequestParam Long userId) {
        return ResponseEntity.ok(seafoodService.mySeafoodList(userId));
    }
}
