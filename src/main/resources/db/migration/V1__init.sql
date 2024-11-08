USE sumbisori;

DROP TABLE IF EXISTS place;
CREATE TABLE place
(
    place_id      BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)          NULL,
    `description` TEXT                  NULL,
    address       VARCHAR(255)          NULL,
    price         INT                   NULL,
    image_url     VARCHAR(255)          NULL,
    latitude      DOUBLE                NULL,
    longitude     DOUBLE                NULL,
    CONSTRAINT pk_place PRIMARY KEY (place_id)
);

DROP TABLE IF EXISTS seafood;
CREATE TABLE seafood
(
    seafood_id    BIGINT AUTO_INCREMENT NOT NULL,
    korean_name   VARCHAR(255)          NULL,
    english_name  VARCHAR(255)          NULL,
    `description` VARCHAR(255)          NULL,
    CONSTRAINT pk_seafood PRIMARY KEY (seafood_id)
);

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    user_id           BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime              NULL,
    updated_at        datetime              NULL,
    email             VARCHAR(255)          NULL,
    provider_type     VARCHAR(20)           NULL,
    provider_id       VARCHAR(255)          NULL,
    user_role         VARCHAR(20)           NULL,
    nickname          VARCHAR(255)          NULL,
    profile_image_url VARCHAR(255)          NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS reservation;
CREATE TABLE reservation
(
    reservation_id   BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime              NULL,
    updated_at       datetime              NULL,
    user_id          BIGINT                NOT NULL,
    place_id         BIGINT                NOT NULL,
    name             VARCHAR(255)          NOT NULL,
    number_of_people INT                   NOT NULL,
    reservation_date VARCHAR(255)          NOT NULL,
    reservation_time VARCHAR(255)          NOT NULL,
    status           VARCHAR(20)           NULL,
    CONSTRAINT pk_reservation PRIMARY KEY (reservation_id)
);

DROP TABLE IF EXISTS seafood_collection;
CREATE TABLE seafood_collection
(
    seafood_collection_id BIGINT AUTO_INCREMENT NOT NULL,
    created_at            datetime              NULL,
    updated_at            datetime              NULL,
    user_id               BIGINT                NULL,
    seafood_id            BIGINT                NOT NULL,
    quantity              INT                   NULL,
    CONSTRAINT pk_seafoodcollection PRIMARY KEY (seafood_collection_id)
);

ALTER TABLE reservation
    ADD CONSTRAINT FK_RESERVATION_ON_PLACE FOREIGN KEY (place_id) REFERENCES place (place_id);

ALTER TABLE seafood_collection
    ADD CONSTRAINT FK_SEAFOODCOLLECTION_ON_SEAFOOD FOREIGN KEY (seafood_id) REFERENCES seafood (seafood_id);

INSERT INTO seafood (korean_name, english_name, description)
VALUES ('전복', 'Abalone', '전복은 진주 빛 껍질과 영양 가득한 살을 지녀 고급 요리 재료로 손색이 없습니다.'),
       ('성게', 'SeaUrchin', '성게는 뾰족한 가시와 오메가-3 지방산이 풍부해 일식에서 특별한 위치를 차지합니다.'),
       ('조개', 'Clam', '조개는 해저를 아름답게 장식하고 수질 정화에 기여하는 필터 피더입니다.'),
       ('보말', 'Omphalius', '제주도의 보말은 강인함을 상징하며, 다양한 요리에서 풍미를 더합니다.'),
       ('소라', 'Conch', '소라는 나선형 껍질과 달콤한 맛으로 요리에 활용되는 인기 해산물입니다.'),
       ('뿔소라', 'Murex', '뿔소라는 뾰족한 껍질과 훌륭한 맛으로 보양식에도 좋습니다.'),
       ('미역', 'SeaMustard', '미역은 건강에 좋은 미네랄과 비타민이 풍부하며 다양한 한국 요리에 사용됩니다.'),
       ('멍게', 'SeaSquirt', '멍게는 쫄깃한 식감과 감칠맛 나는 맛으로 회나 초무침에 인기가 높습니다.'),
       ('홍합', 'Mussel', '홍합은 부드러운 살과 진한 향으로 수프나 파스타에 자주 사용됩니다.'),
       ('고둥', 'Gastropods', '고둥은 작고 견고한 껍질로 칼슘을 많이 함유하고 있어 건강에 좋습니다.'),
       ('굴', 'Oyster', '굴은 "바다의 우유"로 불리며 오메가-3와 미네랄이 풍부해 겨울 별미입니다.'),
       ('문어', 'Octopus', '문어는 뛰어난 지능과 독특한 식감을 가진 해양 생물로 다양한 요리에 사용됩니다.'),
       ('해삼', 'SeaCucumber', '해삼은 콜라겐이 풍부해 피부 건강에 좋으며 고소하고 쫄깃합니다.'),
       ('오징어', 'Squid', '오징어는 빠른 성장률과 잉크 분출로 알려져 있으며 맛이 뛰어납니다.'),
       ('그물조각', 'Net', '바다 속을 떠다니며 해양 생물에게 위험을 초래할 수 있는 폐그물 조각입니다.'),
       ('밧줄', 'Rope', '바다에서 유실된 밧줄로 해양 생물에게 치명적인 영향을 줄 수 있습니다.'),
       ('비닐', 'Vinyl', '바다를 오염시키는 주요 원인 중 하나로, 해양 생물이 잘못 섭취할 위험이 있습니다.'),
       ('물병', 'WaterBottle', '플라스틱 물병으로, 해양 오염을 유발하고 생태계를 위협합니다.');

INSERT INTO place (name, description, address, price, image_url, latitude, longitude)
VALUES ('제주 서귀포 사계어촌체험마을', '안전교육, 환복, 물질 체험, 해산물 시식', '제주 서귀포시 안덕면 형제해안로 13-1 사계어촌계', 50000,
        'https://i.ibb.co/j82zgnG/image.png', 33.227845, 126.305214),
       ('도시해녀', '1일 해녀체험, 장비대여, 수중사진, 샤워용품제공, 해산물시식', '제주특별자치도 제주시 애월읍 하귀미수포길 16-1', 50000,
        'https://i.ibb.co/LPW1BSH/image.jpg', 33.484290, 126.402610),
       ('협재해녀다방&해녀체험', '해녀체험, 장비대여, 수중사진, 온수샤워, 해물라면', '제주 제주시 한림읍 협재1길 42 협재해녀다방', 50000,
        'https://i.ibb.co/Rv4Rr0r/image.jpg', 33.398365, 126.2442661),
       ('모드락바당', '스노클링, 수중플로깅, 해녀 선생님과 물질체험', '제주 서귀포시 성산읍 일출로 264-4', 69000, 'https://i.ibb.co/WGj6xxg/image.jpg',
        33.4610944, 126.933794),
       ('제주 해녀체험', '장비대여, 샤워시설 이용, 해산물시식, 예약 필수', '제주 서귀포시 남원읍 하례망장포로 65-13', 50000,
        'https://i.ibb.co/6JSh8HD/image.jpg', 33.2593087, 126.6394618),
       ('하도어촌체험마을', 'example', '제주특별자치도 제주시 구좌읍 해맞이해안로 1897-27 해녀물질체험', 40000, 'https://i.ibb.co/1zNphk3/image.png',
        33.5200605, 126.9040869),
       ('잠수교육연구협회', 'example', '제주 제주시 한림읍 한림해안로 621 잠수교육연구협회', 60000, 'https://i.ibb.co/8jyV125/image.jpg', 33.442673,
        126.278637),
       ('법환어촌체험휴양마을', 'example', '제주특별자치도 서귀포시 법환로 1 해녀물질체험', 30000, 'https://i.ibb.co/Tm0bQ0V/image.png', 33.2361691,
        126.5146793),
       ('제주바다목장 다이브리조트', 'example', '제주 제주시 한경면 한경해안로 565', 60000, 'https://i.ibb.co/51Ntq63/image.jpg', 33.3460581,
        126.1793415);

