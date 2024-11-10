package ca.gbc.eventservice.service;

import ca.gbc.eventservice.dto.EventRequest;
import ca.gbc.eventservice.dto.EventResponse;
import ca.gbc.eventservice.model.Event;
import ca.gbc.eventservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public EventResponse createEvent(EventRequest eventRequest) {

        Event event = Event.builder()
                .eventName(eventRequest.eventName())
                .organizerId(eventRequest.organizerId())
                .eventType(eventRequest.eventType())
                .expectedAttendees(eventRequest.expectedAttendees())
                .status(eventRequest.status())
                .build();

        eventRepository.save(event);

        log.info("Event {} has been saved", event.getId());

        return new EventResponse(
                event.getId(),
                event.getEventName(),
                event.getOrganizerId(),
                event.getEventType(),
                event.getExpectedAttendees(),
                event.getStatus()
        );
    }

    @Override
    public List<EventResponse> getAllEvents() {
        List<Event> events = eventRepository.findAll();

        return events.stream()
                .map(event -> EventResponse.builder()
                        .id(event.getId())
                        .eventName(event.getEventName())
                        .organizerId(event.getOrganizerId())
                        .eventType(event.getEventType())
                        .expectedAttendees(event.getExpectedAttendees())
                        .status(event.getStatus())
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    public String updateEvent(String id, EventRequest eventRequest) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Event event = mongoTemplate.findOne(query, Event.class);

        if (event == null) {
            throw new RuntimeException("Event with ID " + id + " not found");
        }

        event.setEventName(eventRequest.eventName());
        event.setOrganizerId(eventRequest.organizerId());
        event.setEventType(eventRequest.eventType());
        event.setExpectedAttendees(eventRequest.expectedAttendees());
        event.setStatus(eventRequest.status());

        eventRepository.save(event);

        return "Event with ID " + id + " has been updated";
    }


    @Override
    public void deleteEvent(String id) {

        log.debug("Deleting a event with ID {}", id);
        eventRepository.deleteById(id);

    }
}
