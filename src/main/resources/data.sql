-- Insert roles data only if it doesn't already exist
INSERT INTO roles (role_id, name)
SELECT * FROM (
                  SELECT 1 AS role_id, 'ADMIN' AS name
              ) AS new_roles
WHERE NOT EXISTS (
    SELECT 1 FROM roles WHERE role_id = new_roles.role_id
);

-- Insert users data only if it doesn't already exist
INSERT INTO users (user_id, enabled, password, username)
SELECT * FROM (
                  SELECT 1 AS user_id, b'1' AS enabled, '$2a$10$jPRBKFQ5grjV0QvpSSFbeOXcvXYu1a9ihVrypWwXBAJyi5b67jcfq' AS password, 'admin' AS username
              ) AS new_users
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE user_id = new_users.user_id
);

-- Insert users_roles data only if it doesn't already exist
INSERT INTO users_roles (user_id, role_id)
SELECT * FROM (
                  SELECT 1 AS user_id, 1 AS role_id
              ) AS new_users_roles
WHERE NOT EXISTS (
    SELECT 1 FROM users_roles WHERE user_id = new_users_roles.user_id AND role_id = new_users_roles.role_id
);

-- Insert salle data only if it doesn't already exist
INSERT INTO salle (capacity, numero, type)
SELECT * FROM (
                  SELECT 30 AS capacity, 'Room 1' AS numero, 'Classroom' AS type
                  UNION SELECT 25, 'Room 2', 'Classroom'
                  UNION SELECT 25, 'Room 3', 'Classroom'
                  UNION SELECT 30, 'Room 4', 'Classroom'
                  UNION SELECT 50, 'Room 5', 'Classroom'
                  UNION SELECT 200, 'Amphi A', 'Amphi'
                  UNION SELECT 150, 'Amphi B', 'Amphi'
              ) AS new_salle
WHERE NOT EXISTS (
    SELECT 1 FROM salle WHERE capacity = new_salle.capacity AND numero = new_salle.numero AND type = new_salle.type
);
