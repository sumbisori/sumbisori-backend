package com.groom.sumbisori.domain.badge.entity;

public enum BadgeCode {
    // BASIC TYPE
    FIRST_JOIN,               // 처음 앱 사용 or 회원가입
    FIRST_EXPERIENCE_WRITE,   // 체험일지 첫 작성
    FIRST_COLLECTION,         // 첫 채취

    // RANKED TYPE
    COLLECT_SEONGGE,          // 성게 수확
    COLLECT_GODONG,           // 고동 수확
    COLLECT_BBULSORA,         // 뿔소라 수확
    COLLECT_JEONBOK,          // 전복 수확
    COLLECT_GUL,              // 굴 수확
    COLLECT_MONGGE,           // 멍게 수확
    COLLECT_MUNE,             // 문어 수확
    COLLECT_OJINGEO,          // 오징어 수확
    COLLECT_JOGAE,            // 조개 수확
    COLLECT_MIYEOK,           // 미역 수확
    COLLECT_HAESAM,           // 해삼 수확
    COLLECT_HONGHAP,          // 홍합 수확

    // SPECIAL TYPE
    CLEAN_ALL_TRASH           // 물질도감 해양쓰레기 전부 수집 달성
}
