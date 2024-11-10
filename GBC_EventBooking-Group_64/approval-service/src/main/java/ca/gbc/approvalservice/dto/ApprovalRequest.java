package ca.gbc.approvalservice.dto;

import lombok.Builder;

@Builder
public record ApprovalRequest(
        String eventId,
        Long staffId,
        String status
) {
}
