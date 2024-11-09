package ca.gbc.approvalservice.dto;

import lombok.Builder;

@Builder
public record ApprovalResponse(
        Long id,
        Long staffId,
        String status
) {
}
