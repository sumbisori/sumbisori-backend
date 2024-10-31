package com.groom.demo.domain.user;

import lombok.Builder;

@Builder
public record UserProfile(Long userId, String nickname, String profileImageUrl) {
}
