package ca.gbc.bookingservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    @Id
    private String id;
    private String userId;
    private String roomId;
    private String date;
    private String startTime;
    private String endTime;
    private String purpose;
}