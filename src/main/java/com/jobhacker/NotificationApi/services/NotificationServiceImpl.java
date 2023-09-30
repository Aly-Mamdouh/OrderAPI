package com.jobhacker.NotificationApi.services;

import com.jobhacker.NotificationApi.mappers.NotificationMapper;
import com.jobhacker.NotificationApi.models.dtos.NotificationDto;
import com.jobhacker.NotificationApi.models.entities.Notification;
import com.jobhacker.NotificationApi.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;
    private final NotificationMapper mapper;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper mapper) {
        this.notificationRepository = notificationRepository;
        this.mapper = mapper;
    }

    @Override
    public List<NotificationDto> getNotifications() {
        List<Notification> notifications=notificationRepository.findAll();
        return notifications.stream()
                .map(mapper::mapToDto)
                .toList();
    }
}
