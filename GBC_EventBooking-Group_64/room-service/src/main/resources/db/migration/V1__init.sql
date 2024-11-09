CREATE TABLE t_rooms (
    id SERIAL PRIMARY KEY,
    room_name VARCHAR(255) NOT NULL,
    capacity INTEGER NOT NULL,
    features VARCHAR(255),
    available BOOLEAN DEFAULT TRUE
);
