ALTER TABLE place
    ADD reservation_link VARCHAR(255) NULL;

UPDATE place SET place.reservation_link = 'https://map.naver.com/p/entry/place/35244118?placePath=/ticket&searchType=place&lng=126.6395296858&lat=33.25975689827&c=15.00,0,0,0,dh' WHERE place_id = 5;

ALTER TABLE place
    DROP COLUMN price;

ALTER TABLE place
    ADD COLUMN min_price INT,
    ADD COLUMN max_price INT;

UPDATE place SET min_price = 30000, max_price = 50000 WHERE place_id = 1;
UPDATE place SET min_price = 50000, max_price = 50000 WHERE place_id = 2;
UPDATE place SET min_price = 50000, max_price = 50000 WHERE place_id = 3;
UPDATE place SET min_price = 69000, max_price = 69000 WHERE place_id = 4;
UPDATE place SET min_price = 50000, max_price = 50000 WHERE place_id = 5;
UPDATE place SET min_price = 40000, max_price = 40000 WHERE place_id = 6;
UPDATE place SET min_price = 60000, max_price = 60000 WHERE place_id = 7;
UPDATE place SET min_price = 30000, max_price = 30000 WHERE place_id = 8;
UPDATE place SET min_price = 60000, max_price = 60000 WHERE place_id = 9;
