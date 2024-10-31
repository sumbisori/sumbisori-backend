package com.groom.demo.domain.seafood;

import lombok.Getter;

@Getter
public enum Seafood {
    ABALONE("전복", "Abalone", "전복은 진주 빛 껍질과 영양 가득한 살을 지녀 고급 요리 재료로 손색이 없습니다."),
    SEA_URCHIN("성게", "SeaUrchin", "성게는 뾰족한 가시와 오메가-3 지방산이 풍부해 일식에서 특별한 위치를 차지합니다."),
    CLAM("조개", "Clam", "조개는 해저를 아름답게 장식하고 수질 정화에 기여하는 필터 피더입니다."),
    SEA_CUCUMBER("보말", "Omphalius", "제주도의 보말은 강인함을 상징하며, 다양한 요리에서 풍미를 더합니다."),
    CONCH("소라", "Conch", "소라는 나선형 껍질과 달콤한 맛으로 요리에 활용되는 인기 해산물입니다."),
    HORNED_CONCH("뿔소라", "Murex", "뿔소라는 뾰족한 껍질과 훌륭한 맛으로 보양식에도 좋습니다."),
    WAKAME("미역", "SeaMustard", "미역은 건강에 좋은 미네랄과 비타민이 풍부하며 다양한 한국 요리에 사용됩니다."),
    SEA_SQUIRT("멍게", "SeaSquirt", "멍게는 쫄깃한 식감과 감칠맛 나는 맛으로 회나 초무침에 인기가 높습니다."),
    MUSSEL("홍합", "Mussel", "홍합은 부드러운 살과 진한 향으로 수프나 파스타에 자주 사용됩니다."),
    SMALL_MUSSEL("고둥", "Gastropods", "고둥은 작고 견고한 껍질로 칼슘을 많이 함유하고 있어 건강에 좋습니다."),
    OYSTER("굴", "Oyster", "굴은 '바다의 우유'로 불리며 오메가-3와 미네랄이 풍부해 겨울 별미입니다."),
    OCTOPUS("문어", "Octopus", "문어는 뛰어난 지능과 독특한 식감을 가진 해양 생물로 다양한 요리에 사용됩니다."),
    SEA_CUCUMBER_SECOND("해삼", "SeaCucumber", "해삼은 콜라겐이 풍부해 피부 건강에 좋으며 고소하고 쫄깃습니다."),
    SQUID("오징어", "Squid", "오징어는 빠른 성장률과 잉크 분출로 알려져 있으며 맛이 뛰어납니다."),
    NET("그물조각", "Net", "바다 속을 떠다니며 해양 생물에게 위험을 초래할 수 있는 폐그물 조각입니다."),
    ROPE("밧줄", "Rope", "바다에서 유실된 밧줄로 해양 생물에게 치명적인 영향을 줄 수 있습니다."),
    VINYL("비닐", "Vinyl", "바다를 오염시키는 주요 원인 중 하나로, 해양 생물이 잘못 섭취할 위험이 있습니다."),
    WATER_BOTTLE("물병", "WaterBottle", "플라스틱 물병으로, 해양 오염을 유발하고 생태계를 위협합니다.");

    private final String name;
    private final String englishName;
    private final String description;

    Seafood(String name, String englishName, String description) {
        this.name = name;
        this.englishName = englishName;
        this.description = description;
    }
}
