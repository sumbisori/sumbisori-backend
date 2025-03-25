package com.groom.sumbisori.domain.experience.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CompanionType {
    ALONE("혼자"),
    FRIEND("친구"),
    LOVER("연인"),
    FAMILY("가족"),
    COLLEAGUE("동료"),
    RELATIVE("친척");

    private final String label;
}
