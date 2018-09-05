package com.sleibo.es.todo.handler;

import com.sleibo.es.events.TodoCompletedEvent;
import com.sleibo.es.events.TodoCreatedEvent;
import com.sleibo.es.events.TodoUnompletedEvent;
import com.sleibo.es.todo.domain.TodoEntity;
import com.sleibo.es.todo.persistence.TodoRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ReplayStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("todo")
public class TodoEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoEventHandler.class);

    @Autowired
    private TodoRepository todoRepository;

    @EventHandler
    @AllowReplay
    public void on(TodoCreatedEvent event, ReplayStatus replayStatus) {
        LOGGER.info("Replay status " + replayStatus);
        todoRepository.save(new TodoEntity(event.getId(), event.getText()));
    }

    @EventHandler
    public void on(TodoCompletedEvent event) {
        setTodoCompleted(event.getId(), true);
    }

    @EventHandler
    public void on(TodoUnompletedEvent event) {
        setTodoCompleted(event.getId(), false);
    }

    private void setTodoCompleted(String todoId, boolean value) {
        TodoEntity todo = todoRepository.findById(todoId).orElseThrow(() -> new IllegalArgumentException(String.format("Unable to find todo with id %s", todoId)));

        todo.setCompleted(value);

        todoRepository.save(todo);
    }
}
