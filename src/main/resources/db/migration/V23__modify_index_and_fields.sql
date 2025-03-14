ALTER TABLE seafood_collection
    ADD collected_at date NULL;

ALTER TABLE seafood_collection
    MODIFY collected_at date NOT NULL;

ALTER TABLE seafood_collection
    ADD user_id BIGINT NULL;

ALTER TABLE seafood_collection
    MODIFY user_id BIGINT NOT NULL;

ALTER TABLE seafood_collection_item DROP INDEX idx_user_covering;

CREATE INDEX idx_seafood_collection_user ON seafood_collection (user_id);

ALTER TABLE seafood_collection_item
    DROP COLUMN user_id;

