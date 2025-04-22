ALTER TABLE user_badge
    DROP FOREIGN KEY FK_USER_BADGE_ON_BADGE;

CREATE TABLE badge_level
(
    badge_level_id BIGINT AUTO_INCREMENT NOT NULL,
    badge_id       BIGINT                NOT NULL,
    level          INT                   NOT NULL,
    count          INT                   NOT NULL,
    `description`  VARCHAR(255)          NOT NULL,
    code           VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_badgelevel PRIMARY KEY (badge_level_id)
);

CREATE TABLE seafood_badge_mapping
(
    mapping_id     BIGINT AUTO_INCREMENT NOT NULL,
    badge_level_id BIGINT                NOT NULL,
    seafood_id     BIGINT                NOT NULL,
    CONSTRAINT pk_seafoodbadgemapping PRIMARY KEY (mapping_id)
);

ALTER TABLE user_badge
    ADD badge_level_id BIGINT NULL;

ALTER TABLE user_badge
    MODIFY badge_level_id BIGINT NOT NULL;

ALTER TABLE user_badge
    ADD CONSTRAINT unique_user_badge UNIQUE (user_id, badge_level_id);

ALTER TABLE badge_level
    ADD CONSTRAINT FK_BADGELEVEL_ON_BADGE FOREIGN KEY (badge_id) REFERENCES badge (badge_id);

ALTER TABLE seafood_badge_mapping
    ADD CONSTRAINT FK_SEAFOODBADGEMAPPING_ON_BADGE_LEVEL FOREIGN KEY (badge_level_id) REFERENCES badge_level (badge_level_id);

ALTER TABLE user_badge
    ADD CONSTRAINT FK_USER_BADGE_ON_BADGE_LEVEL FOREIGN KEY (badge_level_id) REFERENCES badge_level (badge_level_id);

ALTER TABLE user_badge
    DROP COLUMN badge_id;

ALTER TABLE user_badge
    DROP COLUMN level;

ALTER TABLE badge
    DROP KEY uk_badge_user_id_type;

ALTER TABLE badge
    DROP COLUMN created_at;

ALTER TABLE badge
    DROP COLUMN updated_at;

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_BADGE_LEVEL FOREIGN KEY (badge_level_id) REFERENCES badge_level (badge_level_id);
