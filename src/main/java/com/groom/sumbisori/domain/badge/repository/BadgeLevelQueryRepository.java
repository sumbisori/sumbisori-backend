package com.groom.sumbisori.domain.badge.repository;

import static com.groom.sumbisori.domain.badge.entity.QBadge.badge;
import static com.groom.sumbisori.domain.badge.entity.QBadgeLevel.badgeLevel;

import com.groom.sumbisori.domain.badge.entity.BadgeCode;
import com.groom.sumbisori.domain.badge.entity.BadgeLevel;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BadgeLevelQueryRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * 배지 레벨과 배지 정보를 패치 조인하여 가져오는 메서드
     */
    public Optional<BadgeLevel> findByCodeAndLevelFetchBadge(BadgeCode code, int level) {
        return Optional.ofNullable(queryFactory
                .selectFrom(badgeLevel)
                .join(badgeLevel.badge, badge).fetchJoin()
                .where(
                        badgeLevel.code.eq(code),
                        badgeLevel.level.eq(level)
                )
                .fetchOne());
    }

}
