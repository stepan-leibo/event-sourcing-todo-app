package com.sleibo.es.event.domain;

import com.sleibo.es.domain.Todo;
import com.sleibo.es.events.CompleteTodoCommand;
import com.sleibo.es.events.CreateTodoCommand;
import com.sleibo.es.events.TodoCompletedEvent;
import com.sleibo.es.events.TodoCreatedEvent;
import com.sleibo.es.events.TodoUnompletedEvent;
import com.sleibo.es.events.UncompleteTodoCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@Entity
public class TodoAggregate extends Todo {

    @AggregateIdentifier
    private String aggregateIdentifier;

    @CommandHandler
    public TodoAggregate(CreateTodoCommand command) {
        apply(new TodoCreatedEvent(command.getId(), command.getText()));
    }

    public TodoAggregate() {
    }

    @CommandHandler
    public void on(CompleteTodoCommand command) {
        apply(new TodoCompletedEvent(command.getId()));
    }

    @CommandHandler
    public void on(UncompleteTodoCommand command) {
        apply(new TodoUnompletedEvent(command.getId()));
    }

    public TodoAggregate(String id, String text) {
        super(id, text);
    }

    @EventSourcingHandler
    protected void on(TodoCreatedEvent event) {
        setId(event.getId());
        setText(event.getText());
        this.aggregateIdentifier = event.getId();
    }

    @EventSourcingHandler
    protected void on(TodoCompletedEvent event) {
        setCompleted(true);
    }

    @EventSourcingHandler
    protected void on(TodoUnompletedEvent event) {
        setCompleted(false);
    }

    public String getAggregateIdentifier() {
        return aggregateIdentifier;
    }
}
