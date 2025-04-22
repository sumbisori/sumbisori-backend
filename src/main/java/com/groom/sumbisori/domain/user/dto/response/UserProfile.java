package com.groom.sumbisori.domain.user.dto.response;

import com.groom.sumbisori.domain.badge.entity.BadgeType;
import lombok.Builder;

@Builder
public record UserProfile(String nickname, String profileImageUrl, String badgeName, BadgeType badgeType,
                          String badgeDescription, int level, Long totalBadgeCount, Long acquiredBadgeCount) {
}
