package com.groom.sumbisori.domain.badge.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.domain.badge.dto.response.BadgeDetail;
import com.groom.sumbisori.domain.badge.dto.response.BadgeStatus;
import com.groom.sumbisori.domain.badge.service.BadgeLookupService;
import com.groom.sumbisori.domain.badge.service.RepresentativeBadgeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/badges")
@RequiredArgsConstructor
public class BadgeController implements BadgeApi {
    private final BadgeLookupService badgeLookupService;
    private final RepresentativeBadgeService representativeBadgeService;

    @Override
    @GetMapping
    public ResponseEntity<List<BadgeStatus>> getMyBadgeStatuses(@LoginUser final Long userId) {
        return ResponseEntity.ok(badgeLookupService.getBadgeStatuses(userId));
    }

    @Override
    @GetMapping("/{badgeId}")
    public ResponseEntity<BadgeDetail> getBadgeDetail(@LoginUser Long userId, @PathVariable Long badgeId) {
        return ResponseEntity.ok(badgeLookupService.getBadgeDetail(userId, badgeId));
    }

    @Override
    @PostMapping("/{badgeId}/representative")
    public ResponseEntity<Void> setRepresentativeBadge(@LoginUser Long userId, @PathVariable Long badgeId) {
        representativeBadgeService.change(userId, badgeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
