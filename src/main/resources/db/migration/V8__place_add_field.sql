ALTER TABLE place
    ADD link VARCHAR(255) NULL;

ALTER TABLE place
    ADD phone_number VARCHAR(255) NULL;

ALTER TABLE place
    MODIFY image_url VARCHAR(1000);

UPDATE place
SET
    `name` = '제주 서귀포 사계어촌체험마을',
    `description` = '해녀와 함께하는 해녀물질체험\n사계 바다에서 잊지 못할 추억을 만들어보세요!\n\n- 안전교육\n  사계리 어촌체험마을 교육장에서 먼저 체험 전 안전 교육을 받아요 (30분)\n- 환복\n  수트, 오리발, 스노클링 마스크를 받아 환복해요.\n- 물질 체험\n  수심이 얕은 바다에서 물질하는 법을 배워요.\n  해녀 삼촌의 지시에 따라 해산물을 채취해요. (1시간)\n- 해산물 시식타임\n  해녀가 손질해주는 해산물 회를 식당에서 맛봐요.\n  라면을 가져가면 해산물을 넣고 끓여드려요.\n\n주차여부: 가능',
    `address` = '제주 서귀포시 안덕면 형제해안로 13-1 사계어촌체험휴양마을',
    `price` = 50000,
    `image_url` = 'https://d26q93l38xa5u3.cloudfront.net/1-%E1%84%89%E1%85%A1%E1%84%80%E1%85%A8%E1%84%8B%E1%85%A5%E1%84%8E%E1%85%A9%E1%86%AB%E1%84%8E%E1%85%A6%E1%84%92%E1%85%A5%E1%86%B7%E1%84%86%E1%85%A1%E1%84%8B%E1%85%B3%E1%86%AF.png',
    `latitude` = 33.2278513685391,
    `longitude` = 126.305078485235,
    `link` = 'https://map.naver.com/p/entry/place/19931412?lng=126.3050211&lat=33.227931&placePath=%2Fhome&searchType=place&c=15.00,0,0,0,dh',
    `phone_number` = '064-792-3090 / 010-4548-6002'
WHERE `place_id` = 1;

UPDATE place
SET
    `name` = '도시해녀',
    `description` = '해녀체험은 1일 해녀가 되어보는 인생체험입니다.\n(4월은 실내체험 진행 5월부터 바다 해녀체험이 진행됩니다)\n\n교육-장비제공-기념촬영-바닷가이동-장비사용법, 잠수방법교육-해산물채취-수중촬영-온수샤워-해물라면시식 순으로 진행되며 이용가능한 연령은 중학생부터 가능합니다.\n\n수영을 못해도 안전장구 착용으로 체험이 가능하며 제주의 아름다운 바다를 즐기실 수 있습니다.\n\n1일 해녀가 되어보는 울트라스펙타클 해녀체험 프로그램입니다! 문어, 뿔소라 잡고 인생사진 찍어볼까요?\n교육, 장비대여, 수중사진, 온수샤워, 샤워용품 제공, 해산물 시식 모든게 포함된 금액입니다!\n\n주차여부: 가능',
    `address` = '제주특별자치도 제주시 애월읍 하귀미수포길 16-1',
    `price` = 50000,
    `image_url` = 'https://d26q93l38xa5u3.cloudfront.net/2-%E1%84%83%E1%85%A9%E1%84%89%E1%85%B5%E1%84%92%E1%85%A2%E1%84%82%E1%85%A7.jpg',
    `latitude` = 33.4842537470554,
    `longitude` = 126.402548064087,
    `link` = 'https://map.naver.com/p/entry/place/1253949639?placePath=%252Fhome%253Fentry%253Dplt&searchType=place&lng=126.4025625&lat=33.4842678&c=15.00,0,0,0,dh',
    `phone_number` = '010-2313-7412'
WHERE `place_id` = 2;

UPDATE place
SET
    `name` = '협재해녀다방&해녀체험',
    `description` = '제주도 하면 ‘해녀’가 빠질 수 없죠?!\n오늘 하루 여러분도 해녀가 되어 아름다운 바닷속을 체험해보세요.\n\n현직 해녀들과 함께 하는 해녀체험\n수영을 못해도 괜찮아요. 오늘 하루 해녀, 해남이 되어 가족, 우정여행을 스펙타클한 이색체험으로 잊지 못할 추억을 남겨보세요^^\n모든 장비 대여 + 수중사진 + 온수샤워(샴푸, 린스, 바디워시, 수건 완비) + 해물라면이 포함된 가격입니다.\n\n주차여부: 가능',
    `address` = '제주 제주시 한림읍 협재1길 42 협재해녀다방',
    `price` = 50000,
    `image_url` = 'https://d26q93l38xa5u3.cloudfront.net/3-%E1%84%92%E1%85%A7%E1%86%B8%E1%84%8C%E1%85%A2%E1%84%92%E1%85%A2%E1%84%82%E1%85%A7%E1%84%83%E1%85%A1%E1%84%87%E1%85%A1%E1%86%BC.jpg',
    `latitude` = 33.3983910256373,
    `longitude` = 126.244242243514,
    `link` = 'https://map.naver.com/p/entry/place/1361108184?placePath=%252Fhome%253Fentry%253Dplt&searchType=place&lng=126.2442570&lat=33.3983833&c=15.00,0,0,0,dh',
    `phone_number` = '0507-1488-1213'
WHERE `place_id` = 3;

UPDATE place
SET
    `name` = '모드락바당',
    `description` = '성산일출봉 옆 수마포구 해변에서 해녀 선생님들과 함께 물질체험을 해보세요.\n다채로운 제주 바닷속을 경험하는 스노클링,\n제주 바다 환경을 지키기 위한 수중 플로깅도 할 수 있어요.',
    `address` = '제주 서귀포시 성산읍 일출로 264-4',
    `price` = 69000,
    `image_url` = 'https://d26q93l38xa5u3.cloudfront.net/4-%E1%84%86%E1%85%A9%E1%84%83%E1%85%B3%E1%84%85%E1%85%A1%E1%86%A8%E1%84%87%E1%85%A1%E1%84%83%E1%85%A1%E1%86%BC.jpg',
    `latitude` = 33.4611035083614,
    `longitude` = 126.933877047986,
    `link` = 'https://map.naver.com/p/entry/place/1192656991?placePath=%252Fhome%253Fentry%253Dplt&searchType=place&lng=126.9338469&lat=33.4611057&c=15.00,0,0,0,dh',
    `phone_number` = '0507-1335-2964'
WHERE `place_id` = 4;

UPDATE place
SET
    `name` = '서귀포 다이브센터 스쿠버다이빙',
    `description` = '특별한 해녀 체험!\n수영을 못해도 OK, 채취한 전복 소라 맛보기 OK!\n안전한 수심과 환경에서 즐기는 이색체험! 아름다운 서귀포 바다에서 특별한 해녀체험으로 환상의 바다를 경험하세요.\n\n☆ 체험내용\n서귀포다이브센터의 해녀체험은 제주도의 해녀 복장을 착용하고 다양한 해산물을 채집하며 즐기는 체험입니다.\n제주도 유일 현직 해남이 안전교육부터 체험진행을 합니다.\n*물질을 나갔을 때에는 서귀포다이브센터 강사님이 진행합니다.\n\n[해녀체험 유의사항]\n• 총 소요시간 2시간, 실제 해녀체험 1시간 진행\n• 장비 대여 + 사진 촬영 + 샤워 시설 이용 + 해산물 시식\n• 청소년부터 이용 가능 (현장 강사 판단에 따라 이용이 제한될 수 있음)\n• 물 때에 따라 운영시간이 다르므로 반드시 사전 문의 후 이용\n• 해산물을 시식해볼 수는 있지만 가져가실 수는 없습니다\n\n주차여부: 가능',
    `address` = '제주 서귀포시 남원읍 하례망장포로 65-13 2층',
    `price` = 50000,
    `image_url` = 'https://d26q93l38xa5u3.cloudfront.net/5-%E1%84%89%E1%85%A5%E1%84%80%E1%85%B1%E1%84%91%E1%85%A9%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%87%E1%85%B3%E1%84%89%E1%85%A6%E1%86%AB%E1%84%90%E1%85%A5.jpg',
    `latitude` = 33.25975689827,
    `longitude` = 126.6395296858,
    `link` = 'https://map.naver.com/p/entry/place/35244118?placePath=%2Fhome&searchType=place&lng=126.6395296858&lat=33.25975689827&c=15.00,0,0,0,dh',
    `phone_number` = '010-4255-4176'
WHERE `place_id` = 5;

UPDATE place
SET
    `name` = '하도어촌체험마을',
    `description` = '40년 이상 경력의 해녀와 함께하는 해녀 물질 체험!\n\n실제 해녀들과 똑같은 장비를 갖추고 바닷속에서 특별한 추억을 만들어 보세요.\n체험이 끝난 후 소라와 문어 시식을 100% 즐길 수 있습니다.\n\n바다로 희망을 찾으러 떠나 보세요!\n\n- 남녀 탈의 샤워실이 따로 갖춰져 있습니다.\n- 샴푸, 린스, 타월 모두 준비되어 있습니다.\n\n- 해녀체험을 하신 분들에 한해 대나무낚시, 바릇잡이, 스노클링 등 다양한 체험을\n  무료로 반나절 동안 즐길 수 있습니다.\n\n주차여부: 가능',
    `address` = '제주특별자치도 제주시 구좌읍 해맞이해안로 1897-27 하도어촌체험휴양마을',
    `price` = 40000,
    `image_url` = 'https://d26q93l38xa5u3.cloudfront.net/6-%E1%84%92%E1%85%A1%E1%84%83%E1%85%A9%E1%84%8B%E1%85%A5%E1%84%8E%E1%85%A9%E1%86%AB%E1%84%8E%E1%85%A6%E1%84%92%E1%85%A5%E1%86%B7%E1%84%86%E1%85%A1%E1%84%8B%E1%85%B3%E1%86%AF.png',
    `latitude` = 33.5200306068917,
    `longitude` = 126.904051568177,
    `link` = 'https://map.naver.com/p/entry/place/1305961045?placePath=%2Fhome&searchType=place&lng=126.904051568177&lat=33.5200306068917&c=15.00,0,0,0,dh',
    `phone_number` = '010-2685-3815 / 064-783-1996'
WHERE `place_id` = 6;

UPDATE place
SET
    `name` = '잠수교육연구협회',
    `description` = '한수풀 해녀학교, 제주관광대학교와 산학협력을 체결하여 해녀문화보존을 위해 힘쓰는 잠수교육 연구협회입니다.\n\n또한 스킨, 스쿠버다이빙 활성화를 위하여 관광체험 상품을 개발하고 많은 이들이 편하게 이용할 수 있도록 합리적인 가격으로 매년 많은 분들이 체험하고 있습니다.\n\n한림읍 이시돌 목장, 협재해수욕장, 금능해수욕장, 한림공원, 비양도 등 주변 관광지와 차로 20분 거리에 위치해 다양한 여행과 함께 즐기기 좋은 최적의 체험입니다.',
    `address` = '제주 제주시 한림읍 한림해안로 621 잠수교육연구협회',
    `price` = 60000,
    `image_url` = 'https://d26q93l38xa5u3.cloudfront.net/7-%E1%84%8C%E1%85%A1%E1%86%B7%E1%84%89%E1%85%AE%E1%84%80%E1%85%AD%E1%84%8B%E1%85%B2%E1%86%A8%E1%84%8B%E1%85%A7%E1%86%AB%E1%84%80%E1%85%AE%E1%84%92%E1%85%A7%E1%86%B8%E1%84%92%E1%85%A7.jpeg',
    `latitude` = 33.4424829514566,
    `longitude` = 126.278667142664,
    `link` = 'https://map.naver.com/p/entry/place/32618929?placePath=%252Fhome%253Fentry%253Dplt&searchType=place&lng=126.2784265&lat=33.4427387&c=15.00,0,0,0,dh',
    `phone_number` = '064-796-1515'
WHERE `place_id` = 7;

UPDATE place
SET
    `name` = '법환어촌체험휴양마을',
    `description` = '아름다운 제주 법환 바다에서 해녀 물질체험을 즐겨보세요! \n법환마을에서는 체험을 하며 소라, 성게 등 다양한 수산물을 만날 수 있답니다! \n직접 잡은 소라를 체험 후 맛보면 입안에 신선함이 한가득 퍼져요.\n\n주차여부 가능',
    `address` = '제주특별자치도 서귀포시 법환로 1 법환어촌체험휴양마을',
    `price` = 30000,
    `image_url` = 'https://d26q93l38xa5u3.cloudfront.net/8-%E1%84%87%E1%85%A5%E1%86%B8%E1%84%92%E1%85%AA%E1%86%AB%E1%84%8B%E1%85%A5%E1%84%8E%E1%85%A9%E1%86%AB%E1%84%8E%E1%85%A6%E1%84%92%E1%85%A5%E1%86%B7%E1%84%92%E1%85%B2%E1%84%8B%E1%85%A3%E1%86%BC%E1%84%86%E1%85%A1%E1%84%8B%E1%85%B3%E1%86%AF.png',
    `latitude` = 33.2361406040232,
    `longitude` = 126.514652296401,
    `link` = 'https://map.naver.com/p/entry/place/1679234013?placePath=%2Fhome&searchType=place&lng=126.514652296401&lat=33.2361406040232&c=15.00,0,0,0,dh',
    `phone_number` = '064-739-7508 / 064-739-7508'
WHERE `place_id` = 8;

UPDATE place
SET
    `name` = '제주바다목장 다이브리조트',
    `description` = '제주바다 목장 다이브리조트는 제주도의 전통 해녀 문화를 직접 체험할 수 있는 특별한 프로그램을 제공합니다.\n\n제주 해녀들은 특별한 장비 없이 나잠어법을 사용하여 얕은 바다에서 소라, 전복, 보말 등을 채취합니다. 이 프로그램을 통해 참가자들은 해녀들의 삶을 눈으로 보고 몸으로 체험할 수 있습니다.\n\n바릇잡이는 얕은 바닷가에서 보말과 소라 등을 맨손으로 잡는 활동으로, 어른과 어린이 모두가 즐길 수 있어 가족 단위로 참여하기에 적합합니다. 아름다운 신창 바다에서 다양한 해양 생물과 함께하는 해녀 체험을 즐겨보세요!\n\n※ 바다 상황에 따라 당일 취소될 수 있습니다.\n※ 사진 촬영은 서비스로 제공되며, 바다 상황이 위험할 경우 촬영보다 안전에 중점을 둡니다.\n\n주차 가능',
    `address` = '제주 제주시 한경면 한경해안로 565',
    `price` = 60000,
    `image_url` = 'https://d26q93l38xa5u3.cloudfront.net/9-%E1%84%8C%E1%85%A6%E1%84%8C%E1%85%AE%E1%84%87%E1%85%A1%E1%84%83%E1%85%A1%E1%84%86%E1%85%A9%E1%86%A8%E1%84%8C%E1%85%A1%E1%86%BC%20%E1%84%83%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%87%E1%85%B3%E1%84%85%E1%85%B5%E1%84%8C%E1%85%A9%E1%84%90%E1%85%B3.jpg',
    `latitude` = 33.3460794083784,
    `longitude` = 126.179341641866,
    `link` = 'https://map.naver.com/p/entry/place/1670651626?placePath=%252Fhome%253Fentry%253Dplt&searchType=place&lng=126.1793129&lat=33.3460875&c=15.00,0,0,0,dh',
    `phone_number` = '0507-1436-0025'
WHERE `place_id` = 9;
