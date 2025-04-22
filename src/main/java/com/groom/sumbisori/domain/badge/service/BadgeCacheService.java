package com.groom.sumbisori.domain.badge.service;

import com.groom.sumbisori.domain.badge.entity.Badge;
import com.groom.sumbisori.domain.badge.repository.BadgeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BadgeCacheService {
    private final BadgeRepository badgeRepository;

    @Cacheable(value = "badges")
    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }
}
