package ca.gbc.approvalservice.dto;

import lombok.Builder;

@Builder
public record ApprovalResponse(
        Long id,
        String eventId,
        Long staffId,
        String status
) {
}
