package com.groom.sumbisori.domain.badge.repository;

import static com.groom.sumbisori.domain.badge.entity.QBadge.badge;

import com.groom.sumbisori.domain.badge.entity.BadgeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BadgeQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Set<BadgeType> findAcquiredBadgeTypesByUserId(Long userId) {
        return queryFactory
                .select(badge.type)
                .from(badge)
                .where(badge.userId.eq(userId))
                .fetch()
                .stream()
                .collect(Collectors.toUnmodifiableSet());
    }
}
