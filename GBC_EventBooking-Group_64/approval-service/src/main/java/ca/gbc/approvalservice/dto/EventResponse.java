package ca.gbc.approvalservice.dto;

import lombok.Builder;

@Builder
public record EventResponse(
        String id,
        String eventName,
        String bookingId,
        String organizerId,
        String eventType,
        Integer expectedAttendees,
        String status
) {
}
