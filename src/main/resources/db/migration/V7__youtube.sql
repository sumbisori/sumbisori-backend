CREATE TABLE youtube
(
    youtube_id    BIGINT AUTO_INCREMENT NOT NULL,
    video_id      VARCHAR(255)          NULL,
    title         VARCHAR(255)          NULL,
    channel_title VARCHAR(255)          NULL,
    thumbnail_url VARCHAR(255)          NULL,
    view_count    DECIMAL               NULL,
    publish_time  datetime              NULL,
    CONSTRAINT pk_youtube PRIMARY KEY (youtube_id)
);
