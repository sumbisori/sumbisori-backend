package com.groom.demo.domain.user;

import lombok.Builder;

@Builder
public record UserProfile(String nickname, int count) {
}
