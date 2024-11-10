package ca.gbc.eventservice.dto;

import lombok.Builder;

@Builder
public record EventResponse(
        String id,
        String eventName,
        String organizerId,
        String eventType,
        Integer expectedAttendees,
        String status
) {
}