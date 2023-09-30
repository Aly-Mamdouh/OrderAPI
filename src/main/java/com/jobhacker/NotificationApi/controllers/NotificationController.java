package com.jobhacker.NotificationApi.controllers;

import com.jobhacker.NotificationApi.models.dtos.NotificationDto;
import com.jobhacker.NotificationApi.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @GetMapping
    public ResponseEntity<List<NotificationDto>> getNotifications(){
        List<NotificationDto> dtos=notificationService.getNotifications();
        return ResponseEntity.ok(dtos);
    }
}
