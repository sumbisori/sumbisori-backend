CREATE TABLE alarm
(
    alarm_id   BIGINT AUTO_INCREMENT NOT NULL,
    user_id    BIGINT                NULL,
    alarm_type SMALLINT              NULL,
    content    VARCHAR(255)          NULL,
    link       VARCHAR(255)          NULL,
    is_read    BIT(1)                NULL,
    is_deleted BIT(1)                NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    CONSTRAINT pk_alarm PRIMARY KEY (alarm_id)
);

CREATE INDEX idx_alarm_user_created ON alarm (user_id, is_deleted, created_at);
