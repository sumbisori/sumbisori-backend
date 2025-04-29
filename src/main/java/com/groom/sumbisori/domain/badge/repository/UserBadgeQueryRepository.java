package com.groom.sumbisori.domain.badge.repository;

import static com.groom.sumbisori.domain.badge.entity.QBadge.badge;
import static com.groom.sumbisori.domain.badge.entity.QBadgeLevel.badgeLevel;
import static com.groom.sumbisori.domain.badge.entity.QUserBadge.userBadge;

import com.groom.sumbisori.domain.badge.dto.common.BadgeIdAndLevel;
import com.groom.sumbisori.domain.badge.entity.UserBadge;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserBadgeQueryRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * 사용자의 최고 레벨 기준으로 배지 ID 조회
     */
    public Map<Long, Integer> findBadgeIdsByHighestLevel(Long userId) {
        List<BadgeIdAndLevel> results = queryFactory
                .select(
                        Projections.constructor(
                                BadgeIdAndLevel.class,
                                userBadge.badgeLevel.badge.id,
                                userBadge.badgeLevel.level.max()
                        ))
                .from(userBadge)
                .where(userBadge.userId.eq(userId))
                .groupBy(userBadge.badgeLevel.badge.id)
                .fetch();
        return results.stream()
                .collect(Collectors.toMap(
                        BadgeIdAndLevel::badgeId,
                        BadgeIdAndLevel::badgeLevel
                ));
    }

    public Optional<Long> findTopUserBadgeByBadgeId(Long userId, Long badgeId) {
        return Optional.ofNullable(
                queryFactory
                        .select(userBadge.badgeLevel.id)
                        .from(userBadge)
                        .join(userBadge.badgeLevel, badgeLevel)
                        .join(badgeLevel.badge, badge)
                        .where(
                                userBadge.userId.eq(userId),
                                badge.id.eq(badgeId)
                        )
                        .orderBy(badgeLevel.level.desc())
                        .fetchFirst()
        );
    }
}
