--liquibase formatted sql

--changeset tonny:v001_create_users_table

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE INDEX idx_users_email ON users(email);

-- changeset tonny:v001_create_card_info_table
CREATE TABLE card_info (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    number VARCHAR(16) NOT NULL,
    holder VARCHAR(255) NOT NULL,
    expiration_date VARCHAR(5) NOT NULL
);

CREATE INDEX idx_card_info_user_id ON card_info(user_id);

ALTER TABLE card_info
ADD CONSTRAINT fk_card_info_user_id
FOREIGN KEY (user_id) REFERENCES users(id)
ON DELETE CASCADE;