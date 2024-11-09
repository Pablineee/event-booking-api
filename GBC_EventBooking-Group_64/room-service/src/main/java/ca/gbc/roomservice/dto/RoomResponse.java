package ca.gbc.roomservice.dto;

import lombok.Builder;

@Builder
public record RoomResponse(
        Long id,
        String roomName,
        Integer capacity,
        String features,
        Boolean available
) {
}
