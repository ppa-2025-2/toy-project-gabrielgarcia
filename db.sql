CREATE TABLE evidence (
    id UUID PRIMARY KEY,
    content_type VARCHAR(255) NOT NULL,
    content BYTEA NOT ALL,
    filename VARCHAR(255) NOT NULL,
    created_at DEFAULT CURRENT_TIMESTAMP
);