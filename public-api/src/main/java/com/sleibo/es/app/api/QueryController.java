package com.sleibo.es.app.api;

import com.sleibo.es.client.EventServiceClient;
import com.sleibo.es.client.TodoServiceClient;
import com.sleibo.es.domain.Todo;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class QueryController {

    private final EventServiceClient eventServiceClient;
    private final TodoServiceClient todoServiceClient;

    @Autowired
    public QueryController() {
        eventServiceClient = new EventServiceClient();
        todoServiceClient = new TodoServiceClient();
    }

    @GetMapping("/events")
    public ResponseEntity<List<Document>> getEvents() {
        return ResponseEntity.ok(eventServiceClient.getEvents());
    }

    @GetMapping(value = "/todos", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(todoServiceClient.getTodos());
    }

    @GetMapping(value = "/todos/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> getTodo(@PathVariable String id) {
        return ResponseEntity.ok(todoServiceClient.getTodo(id));
    }
}
