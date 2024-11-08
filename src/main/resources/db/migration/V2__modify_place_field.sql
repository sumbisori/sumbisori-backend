ALTER TABLE place
    MODIFY description TEXT;

UPDATE place
SET name = '서귀포 다이브센터'
WHERE name = '제주 해녀체험';
