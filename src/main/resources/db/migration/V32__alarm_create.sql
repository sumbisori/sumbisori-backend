CREATE TABLE alarm
(
    alarm_id   BIGINT AUTO_INCREMENT NOT NULL,
    user_id    BIGINT                NOT NULL,
    type       VARCHAR(255)          NOT NULL,
    content    VARCHAR(255)          NOT NULL,
    link       VARCHAR(255)          NULL,
    is_read    BIT(1)                NOT NULL,
    is_deleted BIT(1)                NOT NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    CONSTRAINT pk_alarm PRIMARY KEY (alarm_id)
);

CREATE INDEX idx_alarm_user_created ON alarm (user_id, is_deleted, created_at);
