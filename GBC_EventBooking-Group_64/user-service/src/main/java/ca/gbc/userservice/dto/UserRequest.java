package ca.gbc.userservice.dto;

import lombok.Builder;

@Builder
public record UserRequest(
        String name,
        String email,
        String role,
        String userType
) {
}
