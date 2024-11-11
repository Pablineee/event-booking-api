package ca.gbc.approvalservice.dto;

import lombok.Builder;

@Builder
public record ApprovalResponse(
        Long id,
        EventResponse event,
        Long staffId,
        String status
) {
}
