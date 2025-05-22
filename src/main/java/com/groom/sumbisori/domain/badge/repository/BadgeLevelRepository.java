package com.groom.sumbisori.domain.badge.repository;

import com.groom.sumbisori.domain.badge.entity.BadgeCode;
import com.groom.sumbisori.domain.badge.entity.BadgeLevel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeLevelRepository extends JpaRepository<BadgeLevel, Long> {
    Optional<BadgeLevel> findByCodeAndLevel(BadgeCode badgeCode, int level);
}
