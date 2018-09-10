package com.sleibo.es.app;

import com.sleibo.es.app.websockets.NotificationsSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketsConfig implements WebSocketConfigurer {

    @Autowired
    private NotificationsSocketHandler notificationsSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(notificationsSocketHandler, "/socket/notifications").setAllowedOrigins("*");
    }

}
