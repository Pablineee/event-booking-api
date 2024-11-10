package ca.gbc.bookingservice.dto;

import lombok.Builder;

@Builder
public record BookingRequest(
        String userId,
        String roomId,
        String date,
        String startTime,
        String endTime,
        String purpose
) {
}
