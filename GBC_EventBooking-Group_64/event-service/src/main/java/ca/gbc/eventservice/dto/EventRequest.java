package ca.gbc.eventservice.dto;

import lombok.Builder;

@Builder
public record EventRequest(
        String eventName,
        String bookingId,
        String organizerId,
        String eventType,
        Integer expectedAttendees,
        String status
) {
}
