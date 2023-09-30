package com.jobhacker.NotificationApi.models.dtos;

import lombok.Data;

@Data
public class NotificationDto {

    private int id;
    private String emailFrom;
    private String emailTo;
    private String message;
    private String status;
    private int retries;
}
