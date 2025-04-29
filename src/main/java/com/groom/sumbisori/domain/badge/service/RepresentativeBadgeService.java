package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.error.BadgeErrorcode;
import com.groom.sumbisori.domain.badge.error.BadgeException;
import com.groom.sumbisori.domain.badge.repository.BadgeRepository;
import com.groom.sumbisori.domain.badge.repository.UserBadgeQueryRepository;
import com.groom.sumbisori.domain.user.error.UserErrorCode;
import com.groom.sumbisori.domain.user.error.exception.UserException;
import com.groom.sumbisori.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RepresentativeBadgeService {
    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;
    private final UserBadgeQueryRepository userBadgeQueryRepository;

    public void change(final Long userId, final Long badgeId) {
        badgeRepository.findById(badgeId)
                .orElseThrow(() -> new BadgeException(BadgeErrorcode.BADGE_NOT_FOUND));

        Long badgeLevelId = userBadgeQueryRepository.findTopUserBadgeByBadgeId(userId, badgeId)
                .orElseThrow(() -> new BadgeException(BadgeErrorcode.BADGE_NOT_OWNED));

        userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND))
                .updateRepBadgeLevel(badgeLevelId);
        log.info("User [{}] successfully updated representative badge to BadgeLevel [{}]", userId, badgeLevelId);
    }
}
