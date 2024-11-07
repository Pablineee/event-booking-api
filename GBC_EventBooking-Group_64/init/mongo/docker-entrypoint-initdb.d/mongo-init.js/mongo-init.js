print('START: MongoDB Initialization');

booking_db = db.getSiblingDB('booking-service');
booking_db.createUser(
    {
        user: 'booking_admin',
        pwd: 'password',
        roles: [ {role: 'readWrite', db: 'booking-service'} ]
    }
);
booking_db.createCollection('bookings');

event_db = db.getSiblingDB('event-service');
event_db.createUser(
    {
        user: 'event_admin',
        pwd: 'password',
        roles: [ {role: 'readWrite', db: 'event-service'} ]
    }
);
event_db.createCollection('events');

approval_db = db.getSiblingDB('approval-service');
approval_db.createUser(
    {
        user: 'approval_admin',
        pwd: 'password',
        roles: [ {role: 'readWrite', db: 'approval-service'} ]
    }
);
approval_db.createCollection('approvals');

print('END: MongoDB Initialization');