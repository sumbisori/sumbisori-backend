CREATE TABLE place_description
(
    description_id BIGINT AUTO_INCREMENT NOT NULL,
    title          VARCHAR(255)          NOT NULL,
    `description`  TEXT                  NOT NULL,
    place_id       BIGINT                NULL,
    CONSTRAINT pk_placedescription PRIMARY KEY (description_id)
);

ALTER TABLE place_description
    ADD CONSTRAINT FK_PLACEDESCRIPTION_ON_PLACE FOREIGN KEY (place_id) REFERENCES place (place_id);

ALTER TABLE place
    DROP COLUMN `description`;

-- Place 1
INSERT INTO place_description (title, description, place_id)
VALUES
    ('안전교육', '사계리 어촌체험마을 교육장에서 먼저 체험 전 안전 교육을 받아요 (30분)', 1),
    ('환복', '수트, 오리발, 스노클링 마스크를 받아 환복해요.', 1),
    ('물질체험', '수심이 얕은 바다에서 물질하는 법을 배워요. 해녀 삼촌의 지시에 따라 해산물을 채취해요. (1시간)', 1),
    ('시식', '해녀가 손질해주는 해산물 회를 식당에서 맛봐요. 라면을 가져가면 해산물을 넣고 끓여드려요.', 1);

-- Place 2
INSERT INTO place_description (title, description, place_id)
VALUES
    ('교육', '장비 사용법과 잠수 방법을 교육받아요.', 2),
    ('장비대여', '해녀 체험에 필요한 모든 장비를 대여해드려요.', 2),
    ('해산물채취', '바다로 나가 문어, 뿔소라 등을 채취해요.', 2),
    ('수중촬영', '체험 중 수중사진을 찍어드려요.', 2),
    ('시식', '체험 후 잡은 해산물로 해물라면을 맛보세요.', 2);

-- Place 3
INSERT INTO place_description (title, description, place_id)
VALUES
    ('현직해녀와체험', '현직 해녀들과 함께 바다에서 물질 체험을 해요.', 3),
    ('장비대여', '스노클링 장비, 오리발 등 필요한 장비를 제공해드려요.', 3),
    ('수중사진', '수중에서 촬영한 멋진 사진을 제공합니다.', 3),
    ('온수샤워', '샴푸, 린스, 바디워시, 수건이 완비된 샤워 시설을 이용할 수 있어요.', 3),
    ('해물라면', '체험 후 따뜻한 해물라면을 즐겨보세요.', 3);

-- Place 4
INSERT INTO place_description (title, description, place_id)
VALUES
    ('물질체험', '성산일출봉 옆 수마포구에서 해녀들과 함께 물질 체험을 해요.', 4),
    ('스노클링', '제주의 다채로운 바닷속을 스노클링으로 경험해요.', 4),
    ('수중플로깅', '제주 바다 환경을 지키기 위한 수중 플로깅 활동을 해요.', 4);

-- Place 5
INSERT INTO place_description (title, description, place_id)
VALUES
    ('안전교육', '서귀포 다이브센터 강사님이 안전교육과 체험 진행을 도와드려요.', 5),
    ('물질체험', '제주도의 해녀 복장을 착용하고 다양한 해산물을 채집해요.', 5),
    ('사진촬영', '체험 중 진행된 사진을 제공합니다.', 5),
    ('샤워시설', '온수 샤워 및 편리한 샤워 시설을 이용할 수 있어요.', 5),
    ('시식', '채취한 해산물로 신선한 맛을 즐겨보세요.', 5);

-- Place 6
INSERT INTO place_description (title, description, place_id)
VALUES
    ('안전교육', '40년 경력의 해녀 삼촌들과 함께 안전하게 물질 체험을 시작해요.', 6),
    ('물질체험', '실제 해녀들과 똑같은 장비를 갖추고 바닷속에서 특별한 경험을 해요.', 6),
    ('소라문어시식', '체험 후 잡은 소라와 문어를 맛보세요.', 6),
    ('다양한무료체험', '해녀체험 후 대나무낚시, 바릇잡이, 스노클링 등 다양한 무료 체험도 즐길 수 있어요.', 6);

-- Place 7
INSERT INTO place_description (title, description, place_id)
VALUES
    ('활동', '해녀문화 보존 및 스킨/스쿠버다이빙 체험', 7),
    ('협력기관', '한수풀 해녀학교, 제주관광대학교', 7),
    ('위치', '한림읍', 7),
    ('주차', '미기재', 7);

-- Place 8
INSERT INTO place_description (title, description, place_id)
VALUES
    ('물질체험', '해녀 선생님들과 함께 소라와 성게를 직접 채집해요.', 8),
    ('시식', '잡은 소라를 체험 후 신선하게 맛보세요.', 8),
    ('바다체험', '법환 바다에서 자연과 함께 물질 체험을 즐겨보세요.', 8);

-- Place 9
INSERT INTO place_description (title, description, place_id)
VALUES
    ('바릇잡이', '얕은 바닷가에서 보말과 소라를 맨손으로 채집해요.', 9),
    ('해녀체험', '제주의 전통 해녀 문화를 직접 체험해보세요.', 9),
    ('시식', '체험 중 채집한 소라와 전복을 맛보세요.', 9),
    ('가족참여', '어린이부터 어른까지 모두 즐길 수 있는 가족 단위 체험입니다.', 9);
