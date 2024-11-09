# GBC Event Booking - Group 64

1. **Clone the repository**
    ```bash
    git clone https://github.com/Pablineee/GBC_EventBooking-Group_64.git
    cd GBC_EventBooking-Group_64/GBC_EventBooking-Group_64
    
2. **Ensure Docker Engine is running**

3. **Run following Docker command to execute docker-compose.yml**
   ```bash
   docker-compose -p gbc_eventbooking-group_64 -f docker-compose.yml up -d --build


## Endpoints
#### room-service - **http://localhost:9001/api/room**
#### user-service - **http://localhost:9002/api/booking**
#### booking-service - **http://localhost:9003/api/user**
#### event-service - **http://localhost:9004/api/event**
#### approval-service - **http://localhost:9005/api/approval**
