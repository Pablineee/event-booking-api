package ca.gbc.approvalservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_approvals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long staffId;
    private String status;

}