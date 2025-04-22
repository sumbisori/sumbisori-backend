package com.groom.sumbisori.domain.badge.controller;

import com.groom.sumbisori.common.config.LoginUser;
import com.groom.sumbisori.common.springdoc.ApiExceptionExplanation;
import com.groom.sumbisori.common.springdoc.ApiResponseExplanations;
import com.groom.sumbisori.domain.badge.dto.response.BadgeDetail;
import com.groom.sumbisori.domain.badge.dto.response.BadgeStatus;
import com.groom.sumbisori.domain.badge.error.BadgeErrorcode;
import com.groom.sumbisori.domain.badge.error.BadgeErrorcode.Const;
import com.groom.sumbisori.domain.experience.error.ExperienceErrorcode;
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
    @ApiResponseExplanations(
            errors = {
                    @ApiExceptionExplanation(value = BadgeErrorcode.class, constant = Const.BADGE_NOT_FOUND, name = "배지를 찾을 수 없습니다."),
            }
    )
    ResponseEntity<BadgeDetail> getBadgeDetail(@LoginUser final Long userId, @PathVariable final Long badgeId);
}
