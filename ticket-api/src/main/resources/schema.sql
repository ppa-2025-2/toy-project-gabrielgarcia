CREATE TABLE IF NOT EXISTS tickets (
    id           INTEGER       NOT NULL PRIMARY KEY AUTOINCREMENT,
    user_id      INTEGER       NOT NULL,
    title        VARCHAR(255)  NOT NULL,
    description  TEXT,
    type         VARCHAR(50)   NOT NULL,
    status       VARCHAR(50)   NOT NULL,
    priority     VARCHAR(50)   NOT NULL,
    created_at   TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);