package com.sleibo.es.app.websockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class NotificationsSocketHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationsSocketHandler.class);

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        LOGGER.info("Message received: " + message.getPayload());

        for (WebSocketSession item : sessions) {
            item.sendMessage(new TextMessage(message.getPayload()));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("Session added " + session);
        sessions.add(session);
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOGGER.info("Session removed(error) " + session);
        sessions.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOGGER.info("Session removed(connection closed) " + session);
        sessions.remove(session);
    }

    public void broadcastTextMessage(String notification) {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(notification));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
