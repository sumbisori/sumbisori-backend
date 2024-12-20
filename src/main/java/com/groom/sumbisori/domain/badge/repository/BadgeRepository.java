package com.groom.sumbisori.domain.badge.repository;

import com.groom.sumbisori.domain.badge.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
