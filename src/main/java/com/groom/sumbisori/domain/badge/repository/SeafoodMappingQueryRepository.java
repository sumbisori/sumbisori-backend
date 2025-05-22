package com.groom.sumbisori.domain.badge.repository;

import static com.groom.sumbisori.domain.badge.entity.QBadge.badge;
import static com.groom.sumbisori.domain.badge.entity.QBadgeLevel.badgeLevel;
import static com.groom.sumbisori.domain.badge.entity.QSeafoodBadgeMapping.seafoodBadgeMapping;

import com.groom.sumbisori.domain.badge.entity.SeafoodBadgeMapping;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeafoodMappingQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<SeafoodBadgeMapping> findBySeafoodIds(Set<Long> seafoodIds) {
        return queryFactory
                .selectFrom(seafoodBadgeMapping)
                .join(seafoodBadgeMapping.badgeLevel, badgeLevel).fetchJoin()
                .join(badgeLevel.badge, badge).fetchJoin()
                .where(seafoodBadgeMapping.seafoodId.in(seafoodIds))
                .fetch();
    }
}
