package com.groom.sumbisori.domain.badge.repository;

import com.groom.sumbisori.domain.badge.entity.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {
    boolean existsByUserIdAndBadgeLevelId(Long userId, Long badgeLevelId);
}
