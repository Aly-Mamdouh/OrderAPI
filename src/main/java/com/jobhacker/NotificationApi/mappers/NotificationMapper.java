package com.jobhacker.NotificationApi.mappers;

import com.jobhacker.NotificationApi.models.dtos.NotificationDto;
import com.jobhacker.NotificationApi.models.entities.Notification;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")

public interface NotificationMapper {
    NotificationDto mapToDto(Notification notification);
    Notification mapToEntity(NotificationDto notificationDto);
}
