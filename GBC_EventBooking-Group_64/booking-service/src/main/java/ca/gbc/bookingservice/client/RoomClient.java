package ca.gbc.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "room", url = "http://localhost:9001")
public interface RoomClient {

    @GetMapping( "available/{roomId}")
    boolean roomAvailable(@PathVariable Long roomId);

    // Implement post method to set room's 'available' field to 'false'
}