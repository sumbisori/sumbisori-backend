package com.groom.sumbisori.domain.badge.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.groom.sumbisori.common.config.QuerydslConfig;
import com.groom.sumbisori.domain.badge.entity.Badge;
import com.groom.sumbisori.domain.badge.entity.BadgeType;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({QuerydslConfig.class, BadgeQueryRepository.class})
class BadgeQueryRepositoryTest {
    @Autowired
    private BadgeQueryRepository badgeQueryRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @Test
    void 특정유저의_배지를_조회한다() {
        Long userId = 1L;

        badgeRepository.save(Badge.builder().userId(userId).type(BadgeType.FIRST_LOGIN).build());
        badgeRepository.save(Badge.builder().userId(userId).type(BadgeType.FIRST_EXPERIENCE).build());

        Set<BadgeType> result = badgeQueryRepository.findAcquiredBadgeTypesByUserId(userId);

        assertThat(result).containsExactlyInAnyOrder(BadgeType.FIRST_LOGIN, BadgeType.FIRST_EXPERIENCE);
    }

    @Test
    void 특정유저가_배지를_하나도_가지고_있지않을시_조회한다() {
        Long userId = 2L;

        Set<BadgeType> result = badgeQueryRepository.findAcquiredBadgeTypesByUserId(userId);

        assertThat(result).isEmpty();
    }
}
