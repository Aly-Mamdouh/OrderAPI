package com.jobhacker.NotificationApi.job;

import com.jobhacker.NotificationApi.consumers.OrderConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationJob {

    private final OrderConsumer orderConsumer;

    @Autowired
    public NotificationJob(OrderConsumer orderConsumer) {
        this.orderConsumer = orderConsumer;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at 12 AM
    public void runNotificationJob() {
        orderConsumer.onMessage(null);
    }
}