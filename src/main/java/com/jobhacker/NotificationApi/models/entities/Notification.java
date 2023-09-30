package com.jobhacker.NotificationApi.models.entities;

import com.jobhacker.NotificationApi.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "notification")
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "email_from", nullable = false, length = 145)
    private String emailFrom;
    @Basic
    @Column(name = "email_to", nullable = false, length = 145)
    private String emailTo;
    @Basic
    @Column(name = "message", nullable = false, length = 255)
    private String message;
    @Basic
    @Column(name = "status", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;
    @Basic
    @Column(name = "retries", nullable = false)
    private int retries;

    public Notification(String emailFrom, String emailTo, String message, NotificationStatus status, int retries) {
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.message = message;
        this.status = status;
        this.retries = retries;
    }
}
