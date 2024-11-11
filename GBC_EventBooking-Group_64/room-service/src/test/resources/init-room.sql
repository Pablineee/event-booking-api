CREATE TABLE t_rooms (
    id SERIAL PRIMARY KEY,
    room_name VARCHAR(255) NOT NULL,
    capacity INTEGER NOT NULL,
    features VARCHAR(255),
    available BOOLEAN DEFAULT TRUE
);

INSERT INTO t_rooms (id, room_name, capacity, features, available)
VALUES
    (2, 'Conference Room 1A', 20, 'projector,whiteboard', true),
    (3, 'Meeting Room 1A', 10, 'whiteboard', true),
    (4, 'Lecture Hall 2B', 50, 'projector,audio_system', true);