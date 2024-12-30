package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.dto.response.MyBadgeStatus;
import com.groom.sumbisori.domain.badge.entity.BadgeType;
import com.groom.sumbisori.domain.badge.repository.BadgeQueryRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BadgeLookupService {
    private final BadgeQueryRepository badgeQueryRepository;

    /**
     * 나의 배지 현황 조회
     */
    public List<MyBadgeStatus> getMyBadgeStatuses(final Long userId) {
        Set<BadgeType> acquiredBadgeTypes = badgeQueryRepository.findAcquiredBadgeTypesByUserId(userId);

        return Arrays.asList(BadgeType.values()).stream()
                .map(type -> MyBadgeStatus.of(type, acquiredBadgeTypes.contains(type)))
                .toList();
    }
}
