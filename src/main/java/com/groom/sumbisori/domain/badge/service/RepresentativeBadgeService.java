package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.error.BadgeErrorcode;
import com.groom.sumbisori.domain.badge.error.BadgeException;
import com.groom.sumbisori.domain.badge.repository.BadgeLevelRepository;
import com.groom.sumbisori.domain.badge.repository.UserBadgeRepository;
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
    private final BadgeLevelRepository badgeLevelRepository;
    private final UserBadgeRepository userBadgeRepository;
    private final UserRepository userRepository;

    public void change(final Long userId, final Long badgeLevelId) {
        badgeLevelRepository.findById(badgeLevelId)
                .orElseThrow(() -> new BadgeException(BadgeErrorcode.BADGE_LEVEL_NOT_FOUND));

        // 1. 해당 배지레벨을 획득하지 않았을 경우
        if (!userBadgeRepository.existsByUserIdAndBadgeLevelId(userId, badgeLevelId)) {
            throw new BadgeException(BadgeErrorcode.BADGE_LEVEL_NOT_OWNED);
        }

        userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND))
                .updateRepBadgeLevel(badgeLevelId);
        log.info("User [{}] successfully updated representative badge to BadgeLevel [{}]", userId, badgeLevelId);
    }
}
