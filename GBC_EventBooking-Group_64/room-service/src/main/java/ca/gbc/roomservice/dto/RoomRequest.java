package ca.gbc.roomservice.dto;

import lombok.Builder;

@Builder
public record RoomRequest(
        String roomName,
        Integer capacity,
        String features,
        Boolean available
) {
}
