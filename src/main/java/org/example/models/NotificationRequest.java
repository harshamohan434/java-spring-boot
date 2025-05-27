package org.example.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationRequest {
    Long userId;
    String message;
    String notificationType;
    String status;
}
