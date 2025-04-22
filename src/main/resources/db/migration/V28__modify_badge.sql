DROP TABLE IF EXISTS badge;

CREATE TABLE badge
(
    badge_id    BIGINT AUTO_INCREMENT NOT NULL,
    type        VARCHAR(255)          NOT NULL,
    name        VARCHAR(255)          NOT NULL,
    description VARCHAR(255)          NOT NULL,
    acquisition VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_badge PRIMARY KEY (badge_id)
);

CREATE TABLE badge_level
(
    badge_level_id BIGINT AUTO_INCREMENT NOT NULL,
    badge_id       BIGINT                NOT NULL,
    level          INT                   NOT NULL,
    count          INT                   NOT NULL,
    description    VARCHAR(255)          NOT NULL,
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

CREATE TABLE user_badge
(
    user_badge_id  BIGINT AUTO_INCREMENT NOT NULL,
    created_at     DATETIME              NULL,
    updated_at     DATETIME              NULL,
    user_id        BIGINT                NOT NULL,
    badge_level_id BIGINT                NOT NULL,
    CONSTRAINT pk_user_badge PRIMARY KEY (user_badge_id)
);

ALTER TABLE users
    ADD badge_level_id BIGINT NULL;

ALTER TABLE user_badge
    ADD CONSTRAINT unique_user_badge UNIQUE (user_id, badge_level_id);

ALTER TABLE badge_level
    ADD CONSTRAINT FK_BADGELEVEL_ON_BADGE FOREIGN KEY (badge_id) REFERENCES badge (badge_id);

ALTER TABLE seafood_badge_mapping
    ADD CONSTRAINT FK_SEAFOODBADGEMAPPING_ON_BADGE_LEVEL FOREIGN KEY (badge_level_id) REFERENCES badge_level (badge_level_id);

ALTER TABLE user_badge
    ADD CONSTRAINT FK_USER_BADGE_ON_BADGE_LEVEL FOREIGN KEY (badge_level_id) REFERENCES badge_level (badge_level_id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_BADGE_LEVEL FOREIGN KEY (badge_level_id) REFERENCES badge_level (badge_level_id);
