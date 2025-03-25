CREATE INDEX idx_user_date ON experience (user_id, experience_date DESC);

ALTER TABLE file
    ADD CONSTRAINT uc_file_imageidentifier UNIQUE (image_identifier);
