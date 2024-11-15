package com.groom.sumbisori.domain.user.dto.response;

import lombok.Builder;

@Builder
public record UserProfile(String nickname, String profileImageUrl, int count) {
}
