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
//        List<Approval> approvals = approvalRepository.findAll();
//        return approvals.stream()
//                .map( approval -> ApprovalResponse.builder()
//                        .id(approval.getId())
//                        .approvalName(approval.getApprovalName())
//                        .features(approval.getFeatures())
//                        .capacity(approval.getCapacity())
//                        .available(approval.getAvailable())
//                        .build())
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public ApprovalResponse createApproval(ApprovalRequest approvalRequest) {
//        Approval approval = Approval.builder()
//                .approvalName(approvalRequest.approvalName())
//                .capacity(approvalRequest.capacity())
//                .features(approvalRequest.features())
//                .available(approvalRequest.available())
//                .build();
//
//        Approval savedApproval = approvalRepository.save(approval);
//
//        return ApprovalResponse.builder()
//                .id(savedApproval.getId())
//                .approvalName(savedApproval.getApprovalName())
//                .capacity(savedApproval.getCapacity())
//                .features(savedApproval.getFeatures())
//                .available(savedApproval.getAvailable())
//                .build();
        return null;
    }

    @Override
    public String updateApproval(String approvalId, ApprovalRequest approvalRequest) {
//        Approval approval = approvalRepository.findById(Long.parseLong(approvalId))
//                .orElseThrow(() -> new RuntimeException("Approval with ID " + approvalId + " not found"));
//
//        approval.setApprovalName(approvalRequest.approvalName());
//        approval.setCapacity(approvalRequest.capacity());
//        approval.setFeatures(approvalRequest.features());
//        approval.setAvailable(approvalRequest.available());
//
//        Approval updatedApproval = approvalRepository.save(approval);
//        return String.valueOf(updatedApproval.getId());
        return null;
    }

    @Override
    public void deleteApproval(String approvalId) {
        Approval approval = approvalRepository.findById(Long.parseLong(approvalId))
                .orElseThrow(() -> new RuntimeException("Approval with ID " + approvalId + " not found"));

        approvalRepository.delete(approval);
    }
}
