ALTER TABLE users
    ADD CONSTRAINT unique_provider_id_type UNIQUE (provider_id, provider_type);
