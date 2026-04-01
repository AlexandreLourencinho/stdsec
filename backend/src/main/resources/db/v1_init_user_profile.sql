CREATE TABLE user_profile (
    id BIGSERIAL PRIMARY KEY,
    keycloak_id UUID UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);