# place_description 에서 특정 place_id 의 titile이 "체험준비물" 인 데이터를 찾아 content를 '-' 로 변경
UPDATE place_description
SET content = '-'
WHERE title = '체험준비물'
  AND content IS NULL;
