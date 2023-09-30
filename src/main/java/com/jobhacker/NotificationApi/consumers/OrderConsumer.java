package com.jobhacker.NotificationApi.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobhacker.NotificationApi.enums.NotificationStatus;
import com.jobhacker.NotificationApi.models.entities.Notification;
import com.jobhacker.NotificationApi.models.entities.Order;
import com.jobhacker.NotificationApi.repositories.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;



@Component
public class OrderConsumer implements MessageListener {
    private final JavaMailSender javaMailSender;
    private final NotificationRepository notificationRepository;
    private final ObjectMapper objectMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    public OrderConsumer(JavaMailSender javaMailSender, NotificationRepository notificationRepository, ObjectMapper objectMapper) {
        this.javaMailSender = javaMailSender;
        this.notificationRepository = notificationRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message) {
        try {
            byte[] body = message.getBody();
            String orderBody = new String(body);
            Order order = objectMapper.readValue(orderBody, Order.class);

            String emailFrom = "alimamdouh01156@gmail.com";
            Notification notification = new Notification(emailFrom, order.getCustomerEmail(),
                    " New Order Added With Order ID: " + order.getId(), NotificationStatus.PENDING, 0);
            notificationRepository.save(notification);

            int maxRetries = 3;
            int retries = 0;
            boolean emailSent = false;

            while (retries < maxRetries && !emailSent) {
                try {
                    sendNotificationEmail(notification);
                    emailSent = true;
                    notification.setStatus(NotificationStatus.SENT);
                    LOGGER.info("Email sent successfully");
                } catch (MailException e) {
                    retries++;
                    LOGGER.info(String.format("Email failed to send: %s", e.getMessage()));
                    if (retries >= maxRetries) {
                        notification.setStatus(NotificationStatus.FAILED);
                    }
                }
            }

            notification.setRetries(retries);
            notificationRepository.save(notification);

            LOGGER.info(String.format("received Order: %s",orderBody));
        }  catch (Exception e) {
            LOGGER.error("Error processing order: {}", e.getMessage());
        }
    }

    private void sendNotificationEmail(Notification notification) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(notification.getEmailFrom());
        mail.setTo(notification.getEmailTo());
        mail.setSubject("New Order Notification");
        mail.setText(notification.getMessage());

        try {
            javaMailSender.send(mail);
            LOGGER.info("Mail sent successfully");
        } catch (MailException e) {
            LOGGER.info(String.format("Failed to send mail inside method: %s", e.getMessage()));
        }
    }

}