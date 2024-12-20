package com.groom.sumbisori.domain.badge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BadgeType {
    FIRST_LOGIN("해녀의 초대장", "첫 로그인을 했을 때 획득하는 뱃지"),
    FIRST_EXPERIENCE("물질의 시작", "첫 해녀체험 시 부여되는 뱃지"),
    THIRD_EXPERIENCE("삼비소리", "세 번째 해녀체험 시 부여되는 뱃지"),
    FIRST_COLLECTION("채취의 기쁨", "첫 해산물 수집 시 부여되는 뱃지");

    private final String name;
    private final String description;
}
