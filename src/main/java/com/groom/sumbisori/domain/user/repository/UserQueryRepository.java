package com.groom.sumbisori.domain.user.repository;

import static com.groom.sumbisori.domain.badge.entity.QBadge.badge;
import static com.groom.sumbisori.domain.badge.entity.QBadgeLevel.badgeLevel;
import static com.groom.sumbisori.domain.badge.entity.QUserBadge.userBadge;
import static com.groom.sumbisori.domain.user.entity.QUser.user;

import com.groom.sumbisori.domain.badge.entity.BadgeCode;
import com.groom.sumbisori.domain.user.dto.response.UserProfile;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Long findUserBadgeId(Long userId) {
        return queryFactory
                .select(badge.id)
                .from(user)
                .join(badgeLevel).on(user.badgeLevelId.eq(badgeLevel.id))
                .join(badge).on(badgeLevel.badge.eq(badge))
                .where(user.id.eq(userId))
                .fetchOne();
    }

    public Optional<UserProfile> findUserProfile(Long userId) {
        long totalBadgeCount = BadgeCode.values().length;

        JPQLQuery<Long> acquiredBadgeCountSubquery = JPAExpressions
                .select(badgeLevel.badge.id.countDistinct())
                .from(userBadge)
                .join(badgeLevel).on(userBadge.badgeLevel.id.eq(badgeLevel.id))
                .where(userBadge.userId.eq(userId));

        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(
                        UserProfile.class,
                        user.nickname,
                        user.profileImageUrl,
                        badge.name,
                        badge.type,
                        badge.description,
                        badgeLevel.level,
                        Expressions.constant(totalBadgeCount),
                        acquiredBadgeCountSubquery

                ))
                .from(user)
                .leftJoin(badgeLevel).on(user.badgeLevelId.eq(badgeLevel.id))
                .leftJoin(badge).on(badgeLevel.badge.eq(badge))
                .where(user.id.eq(userId))
                .fetchOne());
    }
}
