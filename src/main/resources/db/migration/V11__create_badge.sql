CREATE TABLE badge
(
    badge_id   BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    user_id    BIGINT                NULL,
    type       VARCHAR(255)          NULL,
    CONSTRAINT pk_badge PRIMARY KEY (badge_id)
);

ALTER TABLE badge
    ADD CONSTRAINT uk_badge_user_id_type UNIQUE (user_id, type);
