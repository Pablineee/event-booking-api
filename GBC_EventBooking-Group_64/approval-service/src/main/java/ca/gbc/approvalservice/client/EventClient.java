package ca.gbc.approvalservice.client;

import ca.gbc.approvalservice.dto.EventResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "event-service", url = "http://event-service:9004", path = "api/event")
public interface EventClient {
    @GetMapping("/{eventId}")
    EventResponse getEvent(@PathVariable("eventId") String eventId);

    @PutMapping("/status/{eventId}/{status}")
    void updateEventStatus(@PathVariable("eventId") String eventId, @PathVariable("status") String status);
}