INSERT INTO roles(id, role)
VALUES (1, 'ROLE_ADMIN')
ON CONFLICT (id) DO UPDATE SET id   = EXCLUDED.id,
                               role = EXCLUDED.role;

INSERT INTO roles(id, role)
VALUES (2, 'ROLE_USER')
ON CONFLICT (id) DO UPDATE SET id   = EXCLUDED.id,
                               role = EXCLUDED.role;

INSERT INTO roles(id, role)
VALUES (3, 'ROLE_ONLY_READING')
ON CONFLICT (id) DO UPDATE SET id   = EXCLUDED.id,
                               role = EXCLUDED.role;

INSERT INTO users(id, fullname, username, email, password)
VALUES (1, 'Dmitry Vinokurov', 'Dmitry88', 'vinokurov1540@bk.ru',
        '$2a$10$05xitMsZC1ApjzBKzWLnEOfqhG2sFsr5qqdnDlxK0lJduQNEZxev')
ON CONFLICT (id) DO UPDATE SET id       = EXCLUDED.id,
                               fullname = EXCLUDED.fullname,
                               username = EXCLUDED.username,
                               email    = EXCLUDED.email,
                               password = EXCLUDED.password;

INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1)
ON CONFLICT (user_id, role_id) DO UPDATE SET user_id   = EXCLUDED.user_id,
                               role_id = EXCLUDED.role_id;