DELETE
FROM place_description;
ALTER TABLE place_description
    AUTO_INCREMENT = 1;

-- place_description 테이블에 데이터 삽입
INSERT INTO place_description (title, content, description, place_id, icon_id, type)
VALUES ('운영시기', '6월~10월', NULL, 1, 1, 'INFO'),
       ('운영시간', '-', NULL, 1, 2, 'INFO'),
       ('포함사항', '-', NULL, 1, 3, 'INFO'),
       ('이용요금', '30,000~50,000원',
        '어린이(초등학교 4학년~6학년) 30,000원\n청소년 이상(중학교 1학년~성인) 50,000원',
        1, 4, 'INFO'),
       ('이용가능 연령', '-', NULL, 1, 5, 'INFO'),
       ('체험준비물', '비누, 수건, 샴푸 등 개인물품', NULL, 1, 6, 'INFO'),
       ('주차가능', NULL, NULL, 1, 7, 'FACILITY');

INSERT INTO place_description (title, content, description, place_id, icon_id, type)
VALUES ('운영시기', '4월~12월', NULL, 2, 1, 'INFO'),
       ('운영시간', '09:00 ~ 15:00', '09:00 / 11:00 / 13:00 / 15:00', 2, 2, 'INFO'),
       ('포함사항', '교육-장비제공-기념촬영-바닷가이동-장비사용법, 잠수방법교육-해산물채취-수중촬영-온수샤워-해물라면시식', NULL, 2, 3, 'INFO'),
       ('이용요금', '50,000원', NULL, 2, 4, 'INFO'),
       ('이용가능 연령', '중학생 이상 가능(14세 이상)', NULL, 2, 5, 'INFO'),
       ('체험준비물', '-', NULL, 2, 6, 'INFO'),
       ('주차가능', NULL, NULL, 2, 7, 'FACILITY'),
       ('카카오채널', 'https://pf.kakao.com/_txjtwj', NULL, 2, 10, 'INQUIRY');

INSERT INTO place_description (title, content, description, place_id, icon_id, type)
VALUES ('운영시기', '-', NULL, 3, 1, 'INFO'),
       ('운영시간', '15:00', NULL, 3, 2, 'INFO'),
       ('포함사항', '모든 장비 대여 + 수중사진 + 온수샤워(샴푸, 린스, 바디워시, 수건 완비) + 해물라면', NULL, 3, 3, 'INFO'),
       ('이용요금', '50,000원', NULL, 3, 4, 'INFO'),
       ('이용가능 연령', '14세 ~ 17세 보호자 동반시 체험 가능(14세미만 이용불가)', NULL, 3, 5, 'INFO'),
       ('체험준비물', '슈트안에 입을 여분의 옷(수영복 또는 속옷), 개인화장품(선크림, 로션 등)', NULL, 3, 6, 'INFO'),
       ('주차가능', NULL, NULL, 3, 7, 'FACILITY'),
       ('카카오채널', 'http://pf.kakao.com/_aVxmYG', NULL, 3, 10, 'INQUIRY'),
       ('인스타그램', 'https://www.instagram.com/haenyeo.dabang', NULL, 3, 9, 'INQUIRY');

INSERT INTO place_description (title, content, description, place_id, icon_id, type)
VALUES ('운영시기', '-', NULL, 4, 1, 'INFO'),
       ('운영시간', '08:00 ~ 20:00', NULL, 4, 2, 'INFO'),
       ('포함사항', '-', NULL, 4, 3, 'INFO'),
       ('이용요금', '69,000원', NULL, 4, 4, 'INFO'),
       ('이용가능 연령', '-', NULL, 4, 5, 'INFO'),
       ('체험준비물', '-', NULL, 4, 6, 'INFO'),
       ('주차가능', NULL, NULL, 4, 7, 'FACILITY');

INSERT INTO place_description (title, content, description, place_id, icon_id, type)
VALUES ('운영시기', '연중무휴', NULL, 5, 1, 'INFO'),
       ('운영시간', '09:00~18:00 / 연중무휴', NULL, 5, 2, 'INFO'),
       ('포함사항', '장비대여 + 샤워시설 이용 + 맛보기 시식', NULL, 5, 3, 'INFO'),
       ('이용요금', '￦80,000(할인가 ￦50,000)', '(최소 2인이상 예약가능)', 5, 4, 'INFO'),
       ('이용가능 연령', '-', NULL, 5, 5, 'INFO'),
       ('체험준비물', '수영복/래쉬가드/반바지 반팔티 여벌 속옷, 수건, 양말', NULL, 5, 6, 'INFO'),
       ('주차가능', NULL, NULL, 5, 7, 'FACILITY');

INSERT INTO place_description (title, content, description, place_id, icon_id, type)
VALUES ('운영시기', '4월~10월', NULL, 6, 1, 'INFO'),
       ('운영시간', '11:00 / 14:30', NULL, 6, 2, 'INFO'),
       ('포함사항', '-', NULL, 6, 3, 'INFO'),
       ('이용요금', '￦40,000', NULL, 6, 4, 'INFO'),
       ('이용가능 연령', '-', NULL, 6, 5, 'INFO'),
       ('체험준비물', '샤워 용품, 여벌 속옷(수영복), 수건', NULL, 6, 6, 'INFO'),
       ('주차가능', NULL, NULL, 6, 7, 'FACILITY');

INSERT INTO place_description (title, content, description, place_id, icon_id, type)
VALUES ('운영시기', '-', NULL, 7, 1, 'INFO'),
       ('운영시간', '10:00 / 11:30 / 13:30 / 15:00', NULL, 7, 2, 'INFO'),
       ('포함사항', '장비 대여 + 수중 사진 촬영 + 샤워실(온수)', NULL, 7, 3, 'INFO'),
       ('이용요금', '44,500원', NULL, 7, 4, 'INFO'),
       ('이용가능 연령', '연령 제한 있음',
        '8세 ~ 10세 (성인 보호자 동반 시 체험 가능)\n11세 이상 (단독 이용 가능)', 7, 5, 'INFO'),
       ('체험준비물', '슬리퍼, 세면도구, 수건, 수영복 또는 여벌의 속옷', NULL, 7, 6, 'INFO');

INSERT INTO place_description (title, content, description, place_id, icon_id, type)
VALUES ('운영시기', '6월~10월', NULL, 8, 1, 'INFO'),
       ('운영시간', '10:00 ~ 15:30', '예약문의는 17시까지 가능', 8, 2, 'INFO'),
       ('포함사항', '-', NULL, 8, 3, 'INFO'),
       ('이용요금', '30,000원', NULL, 8, 4, 'INFO'),
       ('이용가능 연령', '-', NULL, 8, 5, 'INFO'),
       ('체험준비물', '-', NULL, 8, 6, 'INFO'),
       ('주차가능', NULL, NULL, 8, 7, 'FACILITY');

INSERT INTO place_description (title, content, description, place_id, icon_id, type)
VALUES ('운영시기', '-', NULL, 9, 1, 'INFO'),
       ('운영시간', '08:30 ~ 18:30', '매주 수요일 휴무', 9, 2, 'INFO'),
       ('포함사항', '-', NULL, 9, 3, 'INFO'),
       ('이용요금', '70,000~80,000원',
        '어린이(8~13세) 70,000원\n일반(14세~) 80,000원', 9, 4, 'INFO'),
       ('이용가능 연령', '-', NULL, 9, 5, 'INFO'),
       ('체험준비물', '-', NULL, 9, 6, 'INFO'),
       ('주차가능', NULL, NULL, 9, 7, 'FACILITY');
