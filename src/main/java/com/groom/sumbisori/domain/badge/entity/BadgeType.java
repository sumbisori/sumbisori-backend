package com.groom.sumbisori.domain.badge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BadgeType {
    BASIC, // 첫 채집, 첫 글 작성
    RANKED, // 조개 10개 채집 → 금메달
    SPECIAL // 보말 + 성게 조합 뱃지 등
}
