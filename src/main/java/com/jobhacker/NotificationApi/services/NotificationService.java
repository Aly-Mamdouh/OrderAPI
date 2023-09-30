package com.jobhacker.NotificationApi.services;

import com.jobhacker.NotificationApi.models.dtos.NotificationDto;
import java.util.List;


public interface NotificationService {
     List<NotificationDto> getNotifications();
}
