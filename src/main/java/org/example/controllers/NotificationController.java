package org.example.controllers;


import org.example.models.NotificationRequest;
import org.example.models.NotificationResponseDTO;
import org.example.models.Notifications;
import org.example.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/notifications")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @Async
    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest notificationRequest){
        notificationService.sendNotification(notificationRequest);
        return ResponseEntity.accepted().body("Notification Dispatched");
    }

    @Async
    @PostMapping("/send/batch")
    public ResponseEntity<String> sendNotifications(@RequestBody List<NotificationRequest> notificationRequests){
        notificationService.sendNotifications(notificationRequests);

        return ResponseEntity.accepted().body("Notifications dispatched");
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<Notifications>> getUserNotifications(@PathVariable Long userId){
        return ResponseEntity.ok(notificationService.getNotifications(userId));
    }
}
