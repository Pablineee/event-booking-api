package ca.gbc.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "room-service", url = "http://room-service:8080", path = "api/room/")
public interface RoomClient {

    @GetMapping( "available/{roomId}")
    boolean roomAvailable(@PathVariable Long roomId);

    @PostMapping("unavailable/{roomId}")
    void makeUnavailable(@PathVariable Long roomId);
}