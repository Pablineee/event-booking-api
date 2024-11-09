print('START: MongoDB Initialization');

// Booking Service
db = db.getSiblingDB('booking-service');
db.createUser({
    user: 'admin',
    pwd: 'password',
    roles: [{ role: 'readWrite', db: 'booking-service' }]
});
db.createCollection('bookings');

// Event Service
db = db.getSiblingDB('event-service');
db.createUser({
    user: 'admin',
    pwd: 'password',
    roles: [{ role: 'readWrite', db: 'event-service' }]
});
db.createCollection('events');

print('END: MongoDB Initialization');