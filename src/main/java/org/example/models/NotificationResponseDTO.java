package org.example.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class NotificationResponseDTO {
    private Long id;
    private String message;
    private String notificationType;
    private String status;
    private LocalDateTime createdAt;
    private String username;
}
