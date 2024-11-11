package ca.gbc.eventservice.service;

import ca.gbc.eventservice.dto.EventRequest;
import ca.gbc.eventservice.dto.EventResponse;

import java.util.List;

public interface EventService {
    EventResponse createEvent(EventRequest eventRequest);
    EventResponse getEvent(String eventId);
    List<EventResponse> getAllEvents();
    String updateEvent(String eventId, EventRequest eventRequest);
    void deleteEvent(String eventId);
    void updateEventStatus(String eventId, String status);
}
