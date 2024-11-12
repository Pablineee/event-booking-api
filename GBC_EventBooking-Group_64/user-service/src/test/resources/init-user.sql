CREATE TABLE t_users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(255),
    user_type VARCHAR(255) NOT NULL
);

INSERT INTO t_users (id, name, email, role, user_type)
VALUES
    (2, 'Jack Houston', 'houston.j@user.ca', 'project-coordinator', 'faculty'),
    (3, 'Mary Twins', 'twins.m@user.ca', 'full-time', 'student'),
    (4, 'Barbara Riley', 'riley.b@user.ca', 'administrator', 'staff');