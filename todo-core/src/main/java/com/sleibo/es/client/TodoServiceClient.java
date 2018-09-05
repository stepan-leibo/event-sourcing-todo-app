package com.sleibo.es.client;

import com.sleibo.es.domain.Todo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TodoServiceClient {
    private final RestTemplate restTemplate;

    public TodoServiceClient() {
        this.restTemplate = new RestTemplate();
    }

    public List<Todo> getTodos() {
        return restTemplate.exchange(
            "http://localhost:6902/todos",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Todo>>() {
            }).getBody();
    }

    public Todo getTodo(String id) {
        return restTemplate.exchange(
            "http://localhost:6902/todos/" + id,
            HttpMethod.GET,
            null,
            Todo.class).getBody();
    }
}
