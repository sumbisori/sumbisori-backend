-- 1. 새로운 컬럼 추가 (nullable)
ALTER TABLE file ADD ref_id BIGINT NULL;
ALTER TABLE file ADD ref_type VARCHAR(50) NULL;

-- 2. 기존 experience_id → ref_id/ref_type 마이그레이션
UPDATE file
SET ref_id = experience_id,
    ref_type = 'EXPERIENCE'
WHERE experience_id IS NOT NULL;

-- ✅ 3. 컬럼 NOT NULL 변경 (데이터 다 채운 후)
ALTER TABLE file MODIFY ref_id BIGINT NOT NULL;
ALTER TABLE file MODIFY ref_type VARCHAR(50) NOT NULL;

-- 4. 인덱스 교체
CREATE INDEX idx_file_ref ON file (ref_id, ref_type, sequence, image_identifier);
DROP INDEX idx_file_experience_sequence ON file;

-- 5. experience_id 컬럼 제거
ALTER TABLE file DROP COLUMN experience_id;

-- 6. seafood_collection → file 로 insert
INSERT INTO file (
    user_id,
    ref_type,
    ref_id,
    image_identifier,
    sequence,
    created_at,
    updated_at
)
SELECT
    user_id,
    'SEAFOOD_COLLECTION',
    seafood_collection_id,
    image_identifier,
    1,
    created_at,
    created_at
FROM seafood_collection;

-- 7. seafood_collection.image_identifier 제거
ALTER TABLE seafood_collection DROP COLUMN image_identifier;
