package com.groom.demo.domain.seafood;

public enum Seafood {
    JEONBOK("전복", "전복은 진주 및 껍질과 영양 가득한 살을 지녀 고급 요리 재료로 사용됨"),
    SEONGGYE("성게", "성게는 뾰족한 가시와 오메가-3 지방산이 풍부해 일식에서 특별히 사용됨"),
    JOKGYE("조개", "조개는 바닷물을 아름답게 정화하고 주로 찜과 볶음 형태로 활용"),
    BOMAL("보말", "제주도의 보말은 강인함을 상징하며, 다양한 요리에서 활용됨"),
    SOSARA("소사라", "소라는 나선형 껍질과 달콤한 맛으로 보양식에 애용됨"),
    BULSORA("불소라", "불소라는 뾰족한 껍질과 고급스런 맛으로 보양식에서 인기가 높음"),
    MIEOLGYE("미역", "미역은 건강에 좋은 미네랄과 비타민이 풍부하여 다양한 요리에 사용"),
    MYEONGTAE("명태", "명태는 살코기와 감칠맛 나는 맛으로 한국 대표 요리에 인기가 있음"),
    GODUNGH("고등어", "고등어는 작고 고소한 살집과 감칠 맛이 풍부하여 각종 요리에 활용"),
    GONGCHNI("꽁치", "꽁치는 '바다의 우유'로 불리며 오메가-3과 미네랄이 풍부"),
    MUNE("문어", "문어는 다양한 요리에서 특별한 질감과 맛을 제공하는 해양 생물"),
    HESAEBAG("해삼", "해삼은 바다의 보약으로 불리며 건강에 좋으며 고소하고 쫄깃함"),
    OJINGEO("오징어", "오징어는 빠른 성장을 하고 임금 분출을 하며 맛도 뛰어남");

    private final String name;
    private final String description;

    Seafood(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
