package ca.gbc.approvalservice.service;

import ca.gbc.approvalservice.dto.ApprovalResponse;
import ca.gbc.approvalservice.dto.ApprovalRequest;

import java.util.List;

public interface ApprovalService {
    List<ApprovalResponse> getAllApprovals();
    ApprovalResponse createApproval(ApprovalRequest approvalRequest);
    String updateApproval(String approvalId, ApprovalRequest approvalRequest);
    void deleteApproval(String approvalId);
    ApprovalResponse processApprovalRequest(ApprovalRequest approvalRequest);
}