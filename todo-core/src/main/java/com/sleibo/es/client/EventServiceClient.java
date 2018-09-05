package com.sleibo.es.client;

import com.sleibo.es.domain.Todo;
import org.bson.Document;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

public class EventServiceClient {
    private static final String URL = "http://localhost:6907";

    private final RestTemplate restTemplate;

    public EventServiceClient() {
        this.restTemplate = new RestTemplate();
    }

    public UUID createTodo(Todo todo) {
        return restTemplate.exchange(
            URL + "/todos",
            HttpMethod.POST,
            new HttpEntity<>(todo),
            UUID.class).getBody();
    }

    public UUID completeTodo(UUID id) {
        return restTemplate.exchange(
            URL + "/todos/" + id.toString() + "/complete",
            HttpMethod.PUT,
            HttpEntity.EMPTY,
            UUID.class).getBody();
    }

    public UUID uncompleteTodo(UUID id) {
        return restTemplate.exchange(
            URL + "/todos/" + id.toString() + "/uncomplete",
            HttpMethod.PUT,
            HttpEntity.EMPTY,
            UUID.class).getBody();
    }

    public List<Document> getEvents() {
        return restTemplate.exchange(
            URL + "/events",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Document>>() {}).getBody();
    }
}
