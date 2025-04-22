package com.groom.sumbisori.domain.badge.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.domain.badge.dto.response.BadgeDetail;
import com.groom.sumbisori.domain.badge.dto.response.BadgeStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "badges", description = "배지 API")
public interface BadgeApi {
    @Operation(summary = "배지 현황 조회")
    ResponseEntity<List<BadgeStatus>> getMyBadgeStatuses(@LoginUser final Long userId);

    @Operation(summary = "단일 배지 조회")
    ResponseEntity<BadgeDetail> getBadgeDetail(@LoginUser final Long userId, @PathVariable final Long badgeId);
}
