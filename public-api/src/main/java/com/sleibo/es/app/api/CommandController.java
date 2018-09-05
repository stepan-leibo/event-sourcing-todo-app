package com.sleibo.es.app.api;

import com.sleibo.es.client.EventServiceClient;
import com.sleibo.es.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
public class CommandController {

    private final EventServiceClient eventClient = new EventServiceClient();

    @PostMapping("/todos")
    public ResponseEntity<UUID> createTodo(@RequestBody Todo todo) {
        return ResponseEntity.ok(eventClient.createTodo(todo));
    }

    @PutMapping("/todos/{id}/complete")
    public ResponseEntity<Void> completeTodo(@PathVariable UUID id) {
        eventClient.completeTodo(id);

        return ResponseEntity.accepted().build();
    }

    @PutMapping("/todos/{id}/uncomplete")
    public ResponseEntity<Void> uncompleteTodo(@PathVariable UUID id) {
        eventClient.uncompleteTodo(id);

        return ResponseEntity.accepted().build();
    }
}
