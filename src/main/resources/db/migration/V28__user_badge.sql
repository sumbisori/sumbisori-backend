CREATE TABLE user_badge
(
    user_badge_id BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NULL,
    updated_at    datetime              NULL,
    user_id       BIGINT                NOT NULL,
    badge_id      BIGINT                NULL,
    level         VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_user_badge PRIMARY KEY (user_badge_id)
);

ALTER TABLE badge
    ADD acquisition VARCHAR(255) NULL;

ALTER TABLE badge
    ADD `description` VARCHAR(255) NULL;

ALTER TABLE badge
    ADD name VARCHAR(255) NULL;

ALTER TABLE badge
    MODIFY acquisition VARCHAR(255) NOT NULL;

ALTER TABLE badge
    MODIFY `description` VARCHAR(255) NOT NULL;

ALTER TABLE badge
    MODIFY name VARCHAR(255) NOT NULL;

ALTER TABLE users
    ADD badge_level_id BIGINT NULL;

CREATE INDEX idx_user_badge_user_id ON user_badge (user_id);

ALTER TABLE user_badge
    ADD CONSTRAINT FK_USER_BADGE_ON_BADGE FOREIGN KEY (badge_id) REFERENCES badge (badge_id);

ALTER TABLE badge
    DROP COLUMN user_id;

ALTER TABLE badge
    MODIFY type VARCHAR(255) NOT NULL;
