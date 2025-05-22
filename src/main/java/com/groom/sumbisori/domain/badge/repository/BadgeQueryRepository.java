package com.groom.sumbisori.domain.badge.repository;

import static com.groom.sumbisori.domain.badge.entity.QBadge.badge;
import static com.groom.sumbisori.domain.badge.entity.QBadgeLevel.badgeLevel;
import static com.groom.sumbisori.domain.badge.entity.QUserBadge.userBadge;
import static com.groom.sumbisori.domain.user.entity.QUser.user;

import com.groom.sumbisori.domain.badge.dto.response.BadgeDetail;
import com.groom.sumbisori.domain.badge.dto.response.BadgeLevelDetail;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BadgeQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<BadgeDetail> findBadgeDetail(Long userId, Long badgeId) {
        Long representativeBadgeLevelId = queryFactory
                .select(user.badgeLevelId)
                .from(user)
                .where(user.id.eq(userId))
                .fetchFirst();

        return Optional.ofNullable(queryFactory
                .from(badge)
                .join(badgeLevel).on(badgeLevel.badge.id.eq(badge.id))
                .leftJoin(userBadge).on(
                        userBadge.badgeLevel.id.eq(badgeLevel.id)
                                .and(userBadge.userId.eq(userId))
                )
                .where(badge.id.eq(badgeId))
                .orderBy(badgeLevel.level.desc())
                .transform(GroupBy.groupBy(badge.id).as(Projections.constructor(
                        BadgeDetail.class,
                        badge.id,
                        badge.type,
                        badge.name,
                        badge.description,
                        badge.acquisition,
                        GroupBy.list(Projections.constructor(
                                BadgeLevelDetail.class,
                                badgeLevel.id,
                                userBadge.createdAt,
                                badgeLevel.level,
                                userBadge.id.isNotNull(),
                                badgeLevel.description,
                                badgeLevel.id.eq(representativeBadgeLevelId)
                        ))
                ))).get(badgeId));
    }
}
