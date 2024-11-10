package ca.gbc.approvalservice.service;

import ca.gbc.approvalservice.dto.ApprovalRequest;
import ca.gbc.approvalservice.dto.ApprovalResponse;
import ca.gbc.approvalservice.model.Approval;
import ca.gbc.approvalservice.repository.ApprovalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalRepository approvalRepository;

    @Override
    public List<ApprovalResponse> getAllApprovals() {
        List<Approval> approvals = approvalRepository.findAll();
        return approvals.stream()
                .map( approval -> ApprovalResponse.builder()
                        .id(approval.getId())
                        .eventId(approval.getEventId())
                        .staffId(approval.getStaffId())
                        .status(approval.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ApprovalResponse createApproval(ApprovalRequest approvalRequest) {
        Approval approval = Approval.builder()
                .eventId(approvalRequest.eventId())
                .staffId(approvalRequest.staffId())
                .status(approvalRequest.status())
                .build();

        Approval savedApproval = approvalRepository.save(approval);

        return ApprovalResponse.builder()
                .id(savedApproval.getId())
                .eventId(savedApproval.getEventId())
                .staffId(savedApproval.getStaffId())
                .status(savedApproval.getStatus())
                .build();
    }

    @Override
    public String updateApproval(String approvalId, ApprovalRequest approvalRequest) {
        Approval approval = approvalRepository.findById(Long.parseLong(approvalId))
                .orElseThrow(() -> new RuntimeException("Approval with ID " + approvalId + " not found"));

        approval.setEventId(approvalRequest.eventId());
        approval.setStaffId(approvalRequest.staffId());
        approval.setStatus(approvalRequest.status());

        Approval updatedApproval = approvalRepository.save(approval);

        return String.valueOf(updatedApproval.getId());
    }

    @Override
    public void deleteApproval(String approvalId) {
        Approval approval = approvalRepository.findById(Long.parseLong(approvalId))
                .orElseThrow(() -> new RuntimeException("Approval with ID " + approvalId + " not found"));

        approvalRepository.delete(approval);
    }
}
