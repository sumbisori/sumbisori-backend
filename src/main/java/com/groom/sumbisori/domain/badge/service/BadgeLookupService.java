package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.dto.response.BadgeDetail;
import com.groom.sumbisori.domain.badge.dto.response.BadgeStatus;
import com.groom.sumbisori.domain.badge.entity.Badge;
import com.groom.sumbisori.domain.badge.error.BadgeErrorcode;
import com.groom.sumbisori.domain.badge.error.BadgeException;
import com.groom.sumbisori.domain.badge.repository.BadgeQueryRepository;
import com.groom.sumbisori.domain.badge.repository.UserBadgeQueryRepository;
import com.groom.sumbisori.domain.user.repository.UserQueryRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BadgeLookupService {
    private final BadgeCacheService badgeCacheService;
    private final BadgeQueryRepository badgeQueryRepository;
    private final UserBadgeQueryRepository userBadgeQueryRepository;
    private final UserQueryRepository userQueryRepository;

    /**
     * 배지 현황 조회
     */
    public List<BadgeStatus> getBadgeStatuses(final Long userId) {
        // UserBadge에서 사용자가 최고 레벨을 기준으로 배지 Id조회
        Map<Long, Integer> badgeIdsByHighestLevel = userBadgeQueryRepository.findBadgeIdsByHighestLevel(userId);

        // 모든 배지를 조회
        List<Badge> badges = badgeCacheService.getAllBadges();

        // 대표 뱃지 조회
        Long userBadgeId = userQueryRepository.findUserBadgeId(userId);

        return badges.stream()
                .map(badge -> {
                    int badgeLevel = badgeIdsByHighestLevel.getOrDefault(badge.getId(), 0);
                    return BadgeStatus.of(badge, badgeLevel, badgeLevel > 0,
                            badge.getId().equals(userBadgeId));
                })
                .toList();
    }

    /**
     * 배지 단일 조회
     */
    public BadgeDetail getBadgeDetail(final Long userId, final Long badgeId) {
        return badgeQueryRepository.findBadgeDetail(userId, badgeId)
                .orElseThrow(() -> new BadgeException(BadgeErrorcode.BADGE_NOT_FOUND));

    }
}
