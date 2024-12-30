package com.groom.sumbisori.domain.badge.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.domain.badge.dto.response.MyBadgeStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "badges", description = "배지 API")
public interface BadgeApi {
    @Operation(summary = "배지 현황 조회")
    ResponseEntity<List<MyBadgeStatus>> getMyBadgeStatuses(@LoginUser final Long userId);
}
