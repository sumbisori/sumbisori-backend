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

ALTER TABLE seafood_collection
    ADD experience_id BIGINT NOT NULL;

ALTER TABLE seafood_collection
    ADD image_identifier VARCHAR(255) NOT NULL;

CREATE INDEX idx_experience_user_id ON experience (user_id);

CREATE INDEX idx_file_experience_sequence ON file (experience_id, sequence, image_identifier);

CREATE INDEX idx_seafood_collection_experience ON seafood_collection (experience_id);

CREATE INDEX idx_seafood_collection_user ON seafood_collection (user_id);

ALTER TABLE experience
    ADD CONSTRAINT FK_EXPERIENCE_ON_PLACE FOREIGN KEY (place_id) REFERENCES place (place_id);
