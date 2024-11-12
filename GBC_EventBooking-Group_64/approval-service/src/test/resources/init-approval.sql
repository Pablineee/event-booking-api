CREATE TABLE t_approvals (
    id SERIAL PRIMARY KEY,
    event_id VARCHAR(255) NOT NULL,
    staff_id VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
);

INSERT INTO t_approvals (id, event_id, staff_id, status)
VALUES
    (2, '45hg324j5hg23jg543243', 'hege54j32hg3j24hj3g4j', 'approved'),
    (3, '4ggfg345h234gh32432hj', 'r87fd99g7d9f7df9d7f9d', 'approved'),
    (4, 'dfgh454hj5h4h4h32k4j3', '4ggfg345h234gh32432hj', 'denied');