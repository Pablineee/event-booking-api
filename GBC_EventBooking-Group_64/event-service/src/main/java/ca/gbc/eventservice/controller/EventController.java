package ca.gbc.eventservice.controller;

import ca.gbc.eventservice.client.UserClient;
import ca.gbc.eventservice.dto.EventRequest;
import ca.gbc.eventservice.dto.EventResponse;
import ca.gbc.eventservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventResponse> getAllEvents() {
        return eventService.getAllEvents();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{eventId}")
    public EventResponse getEvent(@PathVariable String eventId) {
        return eventService.getEvent(eventId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest eventRequest) {
        EventResponse createdEvent = eventService.createEvent(eventRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/event/" + createdEvent.id());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .body(createdEvent);
    }

    @PutMapping("/status/{eventId}/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEventStatus(@PathVariable("eventId") String eventId, @PathVariable("status") String status) {
        eventService.updateEventStatus(eventId, status);
    }

    @PutMapping("/{eventId}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateEvent(@PathVariable("eventId") String eventId,
                                           @RequestBody EventRequest eventRequest) {

        String updatedEventId = eventService.updateEvent(eventId, eventRequest);

        // Set the location header attribute
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/event/" + updatedEventId);

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable("eventId") String eventId) {

        eventService.deleteEvent(eventId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
