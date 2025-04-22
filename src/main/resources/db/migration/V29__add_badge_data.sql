INSERT INTO badge (type, name, description, acquisition)
VALUES ('BASIC', '훈저옵서예', '해녀의 초대장이 도착했어요', '처음 앱 사용 시 달성'),
       ('BASIC', '체험드링크', '바다에 입성하신 걸 환영해요!', '체험일지 첫 작성 시 달성'),
       ('BASIC', '채취의 기쁨', '당신도 이제 해녀예요!', '첫 채취 시 달성'),
       ('RANKED', '따끔따끔', '맨손으로 잡으면 아플 수 있어요', '성게 채취 시 개수별 메달 획득'),
       ('RANKED', '보말? 고둥?', '제주도에선 ‘소라’를 ‘고둥’이라고 하고 ‘고둥’은 ‘보말’이라고 한다.', '고둥 채취 시 개수별 메달 획득'),
       ('RANKED', '소리확장기', '바닷소리가 들려~', '뿔소라 채취 시 개수별 메달 획득'),
       ('RANKED', '바다의 산삼', '예로부터 보양식으로 여겨졌어요', '전복 채취 시 개수별 메달 획득'),
       ('RANKED', '소금우유', '굴은 단백질, 미네랄, 비타민이 풍부해요', '굴 채취 시 개수별 메달 획득'),
       ('RANKED', '붉은 파인애플', '울퉁불퉁한 겉모습과 주황빛 색을 띄어요', '멍게 채취 시 개수별 메달 획득'),
       ('RANKED', '빨판 파이터', '문어랑 평생 친구~ (놓아주지 않는 친화력?)', '문어 채취 시 개수별 메달 획득'),
       ('RANKED', '먹물 로켓', '오징어는 위험을 감지하면 먹물을 뿜고 도망가요', '오징어 채취 시 개수별 메달 획득'),
       ('RANKED', '자동 필터링', '조개는 바닷물을 걸러서 플랑크톤을 먹고 살아요', '조개 채취 시 개수별 메달 획득'),
       ('RANKED', '초록 실크', '물속에서 유연하게 흐르며 자라는 모습이 마치 부드러운 천 같아요!', '미역 채취 시 개수별 메달 획득'),
       ('RANKED', '바다의 인삼', '예로부터 한약재로 쓰였어요', '해삼 채취 시 개수별 메달 획득'),
       ('RANKED', '바다 부인', '미네랄 성분이 풍부해 피부재생에 도움이 돼요', '홍합 채취 시 개수별 메달 획득'),
       ('SPECIAL', '해양 청소부', '바다가 한층 더 깨끗해졌어요!', '물질도감 해양 쓰레기 전부 수집 달성 시');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '훈저옵서예'), 1, 1, '회원 가입', 'FIRST_JOIN');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '체험드링크'), 1, 1, '첫 작성', 'FIRST_EXPERIENCE_WRITE');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '채취의 기쁨'), 1, 1, '첫 채취', 'FIRST_COLLECTION');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '따끔따끔'), 1, 1, '1개 채취', 'COLLECT_SEONGGE');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '따끔따끔'), 2, 4, '4개 채취', 'COLLECT_SEONGGE');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '따끔따끔'), 3, 7, '7개 채취', 'COLLECT_SEONGGE');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '보말? 고둥?'), 1, 1, '1개 채취', 'COLLECT_GODONG');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '보말? 고둥?'), 2, 4, '4개 채취', 'COLLECT_GODONG');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '보말? 고둥?'), 3, 7, '7개 채취', 'COLLECT_GODONG');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '소리확장기'), 1, 1, '1개 채취', 'COLLECT_BBULSORA');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '소리확장기'), 2, 4, '4개 채취', 'COLLECT_BBULSORA');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '소리확장기'), 3, 7, '7개 채취', 'COLLECT_BBULSORA');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '바다의 산삼'), 1, 1, '1개 채취', 'COLLECT_JEONBOK');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '바다의 산삼'), 2, 4, '4개 채취', 'COLLECT_JEONBOK');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '바다의 산삼'), 3, 7, '7개 채취', 'COLLECT_JEONBOK');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '소금우유'), 1, 1, '1개 채취', 'COLLECT_GUL');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '소금우유'), 2, 4, '4개 채취', 'COLLECT_GUL');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '소금우유'), 3, 7, '7개 채취', 'COLLECT_GUL');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '붉은 파인애플'), 1, 1, '1개 채취', 'COLLECT_MONGGE');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '붉은 파인애플'), 2, 4, '4개 채취', 'COLLECT_MONGGE');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '붉은 파인애플'), 3, 7, '7개 채취', 'COLLECT_MONGGE');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '빨판 파이터'), 1, 1, '1개 채취', 'COLLECT_MUNE');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '빨판 파이터'), 2, 4, '4개 채취', 'COLLECT_MUNE');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '빨판 파이터'), 3, 7, '7개 채취', 'COLLECT_MUNE');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '먹물 로켓'), 1, 1, '1개 채취', 'COLLECT_OJINGEO');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '먹물 로켓'), 2, 4, '4개 채취', 'COLLECT_OJINGEO');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '먹물 로켓'), 3, 7, '7개 채취', 'COLLECT_OJINGEO');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '자동 필터링'), 1, 1, '1개 채취', 'COLLECT_JOGAE');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '자동 필터링'), 2, 4, '4개 채취', 'COLLECT_JOGAE');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '자동 필터링'), 3, 7, '7개 채취', 'COLLECT_JOGAE');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '초록 실크'), 1, 1, '1개 채취', 'COLLECT_MIYEOK');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '초록 실크'), 2, 4, '4개 채취', 'COLLECT_MIYEOK');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '초록 실크'), 3, 7, '7개 채취', 'COLLECT_MIYEOK');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '바다의 인삼'), 1, 1, '1개 채취', 'COLLECT_HAESAM');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '바다의 인삼'), 2, 4, '4개 채취', 'COLLECT_HAESAM');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '바다의 인삼'), 3, 7, '7개 채취', 'COLLECT_HAESAM');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '바다 부인'), 1, 1, '1개 채취', 'COLLECT_HONGHAP');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '바다 부인'), 2, 4, '4개 채취', 'COLLECT_HONGHAP');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '바다 부인'), 3, 7, '7개 채취', 'COLLECT_HONGHAP');

INSERT INTO badge_level (badge_id, level, count, description, code)
VALUES ((SELECT badge_id FROM badge WHERE name = '해양 청소부'), 1, 1, '쓰레기 모두수집', 'CLEAN_ALL_TRASH');

INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_JEONBOK' AND level = 1), 1);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_JEONBOK' AND level = 2), 1);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_JEONBOK' AND level = 3), 1);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_SEONGGE' AND level = 1), 2);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_SEONGGE' AND level = 2), 2);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_SEONGGE' AND level = 3), 2);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_JOGAE' AND level = 1), 3);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_JOGAE' AND level = 2), 3);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_JOGAE' AND level = 3), 3);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_BBULSORA' AND level = 1), 6);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_BBULSORA' AND level = 2), 6);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_BBULSORA' AND level = 3), 6);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_MIYEOK' AND level = 1), 7);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_MIYEOK' AND level = 2), 7);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_MIYEOK' AND level = 3), 7);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_MONGGE' AND level = 1), 8);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_MONGGE' AND level = 2), 8);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_MONGGE' AND level = 3), 8);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_HONGHAP' AND level = 1), 9);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_HONGHAP' AND level = 2), 9);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_HONGHAP' AND level = 3), 9);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_GODONG' AND level = 1), 10);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_GODONG' AND level = 2), 10);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_GODONG' AND level = 3), 10);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_GUL' AND level = 1), 11);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_GUL' AND level = 2), 11);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_GUL' AND level = 3), 11);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_MUNE' AND level = 1), 12);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_MUNE' AND level = 2), 12);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_MUNE' AND level = 3), 12);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_HAESAM' AND level = 1), 13);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_HAESAM' AND level = 2), 13);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_HAESAM' AND level = 3), 13);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_OJINGEO' AND level = 1), 14);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_OJINGEO' AND level = 2), 14);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'COLLECT_OJINGEO' AND level = 3), 14);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'CLEAN_ALL_TRASH' AND level = 1), 15);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'CLEAN_ALL_TRASH' AND level = 1), 16);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'CLEAN_ALL_TRASH' AND level = 1), 17);
INSERT INTO seafood_badge_mapping (badge_level_id, seafood_id)
VALUES ((SELECT badge_level_id FROM badge_level WHERE code = 'CLEAN_ALL_TRASH' AND level = 1), 18);
