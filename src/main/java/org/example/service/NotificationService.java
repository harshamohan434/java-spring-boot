package org.example.service;


import org.example.models.NotificationRequest;
import org.example.models.NotificationResponseDTO;
import org.example.models.Notifications;
import org.example.models.User;
import org.example.repository.NotificationRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    @Async
    public CompletableFuture<Void> sendNotification(NotificationRequest notificationRequest){
        User user = userRepository.findById(notificationRequest.getUserId()).orElseThrow(() ->
                new RuntimeException("User not found"));

        Notifications notifications = Notifications.builder()
                .user(user)
                .message(notificationRequest.getMessage())
                .notiifcationType(notificationRequest.getNotificationType())
                .status("pending")
                .build();

        notificationRepository.save(notifications);
        return CompletableFuture.completedFuture(null);

    }

    @Async
    public CompletableFuture<Void> sendNotifications(List<NotificationRequest> notificationRequests){
        List<CompletableFuture<Notifications>> futures = notificationRequests.stream()
                .map(req -> CompletableFuture.supplyAsync(() -> {
                    try{
                        System.out.println("thread executing :"+ Thread.currentThread().getName());
                        Thread.sleep(10000);
                    }catch (InterruptedException ex){
                        Thread.currentThread().interrupt();
                    }
                    User user = userRepository.findById(req.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
                    return Notifications.builder()
                            .user(user)
                            .message(req.getMessage())
                            .notiifcationType(req.getNotificationType())
                            .status(req.getStatus() == null ? "pending" : req.getStatus())
                            .build();
                })).collect(Collectors.toList());

        List<Notifications> batch = futures.stream().map(CompletableFuture :: join)
                .collect(Collectors.toList());

        notificationRepository.saveAll(batch);
        return CompletableFuture.completedFuture(null);
    }

    public List<Notifications> getNotifications(Long userId){
        return notificationRepository.findByUserId(userId)
                .stream()
                .collect(Collectors.toList());
    }
    public NotificationResponseDTO mapToDto(Notifications notification) {
        return NotificationResponseDTO.builder()
                .id(notification.getId())
                .message(notification.getMessage())
                .notificationType(notification.getNotiifcationType())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .username(notification.getUser().getUsername())
                .build();
    }
}
