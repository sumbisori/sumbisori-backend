package com.groom.sumbisori.domain.place;

import lombok.Getter;

@Getter
public enum Place {
    SA_GYE_ORCHUN_EXPERIENCE_VILLAGE("제주 서귀포 사계어촌체험마을", "안전교육, 환복, 물질 체험, 해산물 시식", "제주 서귀포시 안덕면 형제해안로 13-1 사계어촌계",
            50000,
            "https://i.ibb.co/j82zgnG/image.png",
            33.227845, 126.305214),

    CITY_HAE_NAE("도시해녀", "1일 해녀체험, 장비대여, 수중사진, 샤워용품제공, 해산물시식", "제주특별자치도 제주시 애월읍 하귀미수포길 16-1", 50000,
            "https://i.ibb.co/LPW1BSH/image.jpg",
            33.484290, 126.402610),


    HYEOB_JAE_HAE_NAE_DABANG_EXPERIENCE("협재해녀다방&해녀체험", "해녀체험, 장비대여, 수중사진, 온수샤워, 해물라면", "제주 제주시 한림읍 협재1길 42 협재해녀다방",
            50000,
            "https://i.ibb.co/Rv4Rr0r/image.jpg",
            33.398365, 126.2442661),

    MODE_RACK_BADANG("모드락바당", "스노클링, 수중플로깅, 해녀 선생님과 물질체험", "제주 서귀포시 성산읍 일출로 264-4", 69000,
            "https://i.ibb.co/WGj6xxg/image.jpg",
            33.4610944, 126.933794),

    JEJU_HAE_NAE_EXPERIENCE("제주 해녀체험", "장비대여, 샤워시설 이용, 해산물시식, 예약 필수", "제주 서귀포시 남원읍 하례망장포로 65-13", 50000,
            "https://i.ibb.co/6JSh8HD/image.jpg",
            33.2593087, 126.6394618),

    ASD("하도어촌체험마을","example","제주특별자치도 제주시 구좌읍 해맞이해안로 1897-27 해녀물질체험",
            40000,
            "https://i.ibb.co/1zNphk3/image.png",
            33.5200605,126.9040869),
    asd("잠수교육연구협회", "example", "제주 제주시 한림읍 한림해안로 621 잠수교육연구협회", 60000,
            "https://i.ibb.co/8jyV125/image.jpg",
            33.442673, 126.278637),
    zxc("법환어촌체험휴양마을", "example", "제주특별자치도 서귀포시 법환로 1 해녀물질체험", 30000,
            "https://i.ibb.co/Tm0bQ0V/image.png",
            33.2361691, 126.5146793),
    zzz("제주바다목장 다이브리조트", "example", "제주 제주시 한경면 한경해안로 565", 60000,
            "https://i.ibb.co/51Ntq63/image.jpg", 33.3460581, 126.1793415
    );

    private final String name;
    private final String description;
    private final String address;
    private final int price;
    private final String imageUrl;
    private double latitude;
    private double longitude;

    Place(String name, String description, String address, int price, String imageUrl,
          double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.price = price;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
