print('START: MongoDB Initialization');

// Booking Service
db = db.getSiblingDB('booking-service');
db.createUser({
    user: 'admin',
    pwd: 'password',
    roles: [{ role: 'readWrite', db: 'booking-service' }]
});
db.createCollection('bookings');
// db.bookings.insertMany([
//     { booking_id: 'B001', user_id: 'U001', room_id: 'R001', date: '2024-11-09', start_time: '15:00', end_time: '17:00', purpose: 'meeting' },
//     { booking_id: 'B002', user_id: 'U002', room_id: 'R002', date: '2024-11-10', start_time: '08:00', end_time: '10:00', purpose: 'study' }
// ]);

// Event Service
db = db.getSiblingDB('event-service');
db.createUser({
    user: 'admin',
    pwd: 'password',
    roles: [{ role: 'readWrite', db: 'event-service' }]
});
db.createCollection('events');

print('END: MongoDB Initialization');