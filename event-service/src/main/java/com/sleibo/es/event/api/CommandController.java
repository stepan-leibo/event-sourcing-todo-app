package com.sleibo.es.event.api;

import com.sleibo.es.domain.Project;
import com.sleibo.es.domain.Todo;
import com.sleibo.es.events.AddTodosToProjectCommand;
import com.sleibo.es.events.CompleteTodoCommand;
import com.sleibo.es.events.CreateProjectCommand;
import com.sleibo.es.events.CreateTodoCommand;
import com.sleibo.es.events.RemoveTodosFromProjectCommand;
import com.sleibo.es.events.UncompleteTodoCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class CommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("/todos")
    public ResponseEntity<UUID> createTodo(@RequestBody Todo todo) {
        UUID id = todo.getId() != null ? UUID.fromString(todo.getId()) : UUID.randomUUID();
        commandGateway.send(new CreateTodoCommand(id.toString(), todo.getText()));


        return ResponseEntity.ok(id);
    }

    @PutMapping("/todos/{id}/complete")
    public ResponseEntity<Void> completeTodo(@PathVariable UUID id) {
        commandGateway.send(new CompleteTodoCommand(id.toString()));

        return ResponseEntity.accepted().build();
    }

    @PutMapping("/todos/{id}/uncomplete")
    public ResponseEntity<Void> uncompleteTodo(@PathVariable UUID id) {
        commandGateway.send(new UncompleteTodoCommand(id.toString()));

        return ResponseEntity.accepted().build();
    }

    @PostMapping("/projects")
    public ResponseEntity<UUID> createProject(@RequestBody Project project) {
        UUID id = project.getId() != null ? UUID.fromString(project.getId()) : UUID.randomUUID();
        commandGateway.send(new CreateProjectCommand(id.toString(), project.getName()));

        return ResponseEntity.ok(id);
    }

    @PostMapping("/project/{id}/_addtodos")
    public ResponseEntity<Void> addTodosToProject(@PathVariable UUID id, @RequestBody List<UUID> todoIds) {
        commandGateway.send(new AddTodosToProjectCommand(id.toString(), todoIds));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/project/{id}/_removetodos")
    public ResponseEntity<Void> removeTodosFromProject(@PathVariable UUID id, @RequestBody List<UUID> todoIds) {
        commandGateway.send(new RemoveTodosFromProjectCommand(id.toString(), todoIds));

        return ResponseEntity.ok().build();
    }
}
