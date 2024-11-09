CREATE TABLE t_approvals (
    id SERIAL PRIMARY KEY,
    staff_id VARCHAR(255) NOT NULL,
    status VARCHAR(15) NOT NULL
);
