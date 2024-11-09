package ca.gbc.bookingservice.dto;

import lombok.Builder;

@Builder
public record BookingResponse(
        String bookingId,
        String userId,
        String roomId,
        String startTime,
        String endTime,
        String purpose
) {
}
