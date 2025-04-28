UPDATE badge
SET description = '제주도에선 ‘소라’를 ‘고둥’이라고 하고\n‘고둥’은 ‘보말’이라고 한다.'
WHERE badge_id = 5;

UPDATE badge
SET description = '이젠 문어랑 평생 친구~\n빨판에 잡히면 한동안 못 빠져나와요~!'
WHERE badge_id = 10;

UPDATE badge
SET description = '물속에서 유연하게 흐르며 자라는 모습이\n 마치 부드러운 천 같아요!'
WHERE badge_id = 13;

UPDATE badge_level
SET description = '4종 수집'
WHERE badge_level_id = 40;
