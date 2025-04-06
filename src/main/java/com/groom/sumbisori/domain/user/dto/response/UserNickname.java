package com.groom.sumbisori.domain.user.dto.response;

public record UserNickname(String nickname) {
    public static UserNickname of(String nickname) {
        return new UserNickname(nickname);
    }
}
