DELETE FROM workstations;
DELETE FROM islands;
DELETE FROM users_roles;
DELETE FROM profiles;
DELETE FROM users;
DELETE FROM roles;

INSERT INTO roles (name) VALUES
('ROLE_USER'),
('ROLE_GUEST'),
('ROLE_VIEWER');

INSERT INTO users (handle, email, password, created_at) 
VALUES ('gabriel', 'gabriel@ifrs.com', 'minhasenha123', CURRENT_TIMESTAMP);

INSERT INTO profiles (id, name, company, type) 
VALUES (
    (SELECT id FROM users WHERE handle = 'gabriel'),
    'Gabriel Garcia',
    'IFRS',
    'FREE'
);

INSERT INTO users_roles (user_id, role_id)
VALUES (
    (SELECT id FROM users WHERE handle = 'gabriel'),
    (SELECT id FROM roles WHERE name = 'ROLE_USER')
);

INSERT INTO islands (disposition, content, created_at, updated_at) 
VALUES ('SQUARE', 'Island Teste', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO workstations (id, island_id, user_id, specs, created_at, updated_at)
VALUES 
    ('WS-001', (SELECT id FROM islands WHERE content = 'Island Teste'), NULL, 'Spec 1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('WS-002', (SELECT id FROM islands WHERE content = 'Island Teste'), NULL, 'Spec 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('WS-003', (SELECT id FROM islands WHERE content = 'Island Teste'), NULL, 'Spec 3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('WS-004', (SELECT id FROM islands WHERE content = 'Island Teste'), NULL, 'Spec 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);