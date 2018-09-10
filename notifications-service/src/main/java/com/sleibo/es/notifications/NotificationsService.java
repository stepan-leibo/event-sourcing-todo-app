package com.sleibo.es.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(NotificationsServiceConfig.class)
public class NotificationsService {
    public static void main(String[] args) {
        SpringApplication.run(NotificationsService.class, args);
    }
}
