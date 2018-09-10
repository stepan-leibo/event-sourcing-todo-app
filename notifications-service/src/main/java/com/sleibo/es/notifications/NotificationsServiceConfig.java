package com.sleibo.es.notifications;

import com.sleibo.es.notifications.handler.NotificationsEventHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({MessagingConfig.class, NotificationsEventHandler.class})
public class NotificationsServiceConfig {
}
