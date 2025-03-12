DROP TABLE IF EXISTS seafood_collection;

CREATE TABLE experience
(
    experience_id   BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime              NULL,
    updated_at      datetime              NULL,
    user_id         BIGINT                NULL,
    place_id        BIGINT                NOT NULL,
    experience_date date                  NOT NULL,
    weather         VARCHAR(255)          NOT NULL,
    companion_type  VARCHAR(255)          NOT NULL,
    impression      VARCHAR(1000)         NULL,
    satisfaction    INT                   NOT NULL,
    CONSTRAINT pk_experience PRIMARY KEY (experience_id)
);

CREATE TABLE file
(
    file_id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime              NULL,
    updated_at       datetime              NULL,
    experience_id    BIGINT                NOT NULL,
    image_identifier VARCHAR(255)          NOT NULL,
    sequence         INT                   NOT NULL,
    CONSTRAINT pk_file PRIMARY KEY (file_id)
);

CREATE TABLE seafood_collection
(
    seafood_collection_id BIGINT AUTO_INCREMENT NOT NULL,
    created_at            datetime              NULL,
    updated_at            datetime              NULL,
    experience_id         BIGINT                NOT NULL,
    image_identifier      VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_seafood_collection PRIMARY KEY (seafood_collection_id)
);

CREATE TABLE seafood_collection_item
(
    collection_item_id    BIGINT AUTO_INCREMENT NOT NULL,
    created_at            datetime              NULL,
    updated_at            datetime              NULL,
    user_id               BIGINT                NOT NULL,
    seafood_collection_id BIGINT                NOT NULL,
    seafood_id            BIGINT                NOT NULL,
    quantity              INT                   NOT NULL,
    CONSTRAINT pk_seafood_collection_item PRIMARY KEY (collection_item_id)
);

CREATE INDEX idx_experience_user_id ON experience (user_id);

CREATE INDEX idx_file_experience_sequence ON file (experience_id, sequence, image_identifier);

CREATE INDEX idx_seafood_collection_covering ON seafood_collection_item (seafood_collection_id, seafood_id, quantity);

CREATE INDEX idx_seafood_collection_experience ON seafood_collection (experience_id);

CREATE INDEX idx_user_covering ON seafood_collection_item (user_id, seafood_id, quantity);

ALTER TABLE experience
    ADD CONSTRAINT FK_EXPERIENCE_ON_PLACE FOREIGN KEY (place_id) REFERENCES place (place_id);

ALTER TABLE seafood_collection_item
    ADD CONSTRAINT FK_SEAFOOD_COLLECTION_ITEM_ON_SEAFOOD FOREIGN KEY (seafood_id) REFERENCES seafood (seafood_id);

DROP TABLE IF EXISTS reservation;
