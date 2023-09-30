package com.jobhacker.NotificationApi.enums;

public enum NotificationStatus {
    PENDING, // Notification is pending and has not been sent yet
    SENT,    // Notification has been successfully sent
    READ,    // Notification has been sent and read by the customer
    FAILED   // Notification failed to send after maximum retries
}
