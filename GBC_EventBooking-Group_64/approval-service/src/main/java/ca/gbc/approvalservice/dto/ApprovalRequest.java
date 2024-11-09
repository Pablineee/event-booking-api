package ca.gbc.approvalservice.dto;

import lombok.Builder;

@Builder
public record ApprovalRequest(
        Long staffId,
        String status
) {
}
