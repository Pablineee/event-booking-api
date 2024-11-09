package ca.gbc.userservice.dto;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String name,
        String email,
        String role,
        String userType
) {
}
