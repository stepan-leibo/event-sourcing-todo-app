package com.sleibo.es.notifications.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sleibo.es.events.TodoCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
@ProcessingGroup("notifications")
public class NotificationsEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationsEventHandler.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private WebSocketSession webSocketSession;

    @EventHandler
    @AllowReplay
    public void on(TodoCreatedEvent event, @Timestamp Instant instant) throws ExecutionException, InterruptedException, TimeoutException, IOException {
        LOGGER.info("Event time " + instant);
        getWebSocketSession().sendMessage(new TextMessage(MAPPER.writeValueAsString(event)));
    }

    private WebSocketSession getWebSocketSession() throws InterruptedException, ExecutionException, TimeoutException {
        if (webSocketSession == null || !webSocketSession.isOpen()) {
            WebSocketClient webSocketClient = new StandardWebSocketClient();
            webSocketSession = webSocketClient
                .doHandshake(new MyWebSocketHandler(), "ws://localhost:6901/socket/notifications")
                .get(3L, TimeUnit.SECONDS);
        }

        return webSocketSession;
    }

    static class MyWebSocketHandler extends AbstractWebSocketHandler {
    }
}
