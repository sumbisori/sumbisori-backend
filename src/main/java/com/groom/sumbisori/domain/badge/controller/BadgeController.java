package com.groom.sumbisori.domain.badge.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.domain.badge.dto.response.MyBadgeStatus;
import com.groom.sumbisori.domain.badge.service.BadgeLookupService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/badges")
@RequiredArgsConstructor
public class BadgeController implements BadgeApi {
    private final BadgeLookupService badgeLookupService;

    @Override
    @GetMapping
    public ResponseEntity<List<MyBadgeStatus>> getMyBadgeStatuses(@LoginUser final Long userId) {
        return ResponseEntity.ok(badgeLookupService.getMyBadgeStatuses(userId));
    }
}
