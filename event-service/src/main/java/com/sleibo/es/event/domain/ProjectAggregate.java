package com.sleibo.es.event.domain;

import com.sleibo.es.domain.Project;
import com.sleibo.es.events.AddTodosToProjectCommand;
import com.sleibo.es.events.CreateProjectCommand;
import com.sleibo.es.events.ProjectCreatedEvent;
import com.sleibo.es.events.RemoveTodosFromProjectCommand;
import com.sleibo.es.events.TodosAddedToProjectEvent;
import com.sleibo.es.events.TodosRemovedFromProjectEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@Entity
public class ProjectAggregate extends Project {

    @AggregateIdentifier
    private String aggregateIdentifier;

    @CommandHandler
    public ProjectAggregate(CreateProjectCommand command) {
        apply(new ProjectCreatedEvent(command.getId(), command.getText()));
    }

    @CommandHandler
    public void on(AddTodosToProjectCommand command) {
        apply(new TodosAddedToProjectEvent(command.getProjectId(), command.getTodoIds()));
    }

    @CommandHandler
    public void on(RemoveTodosFromProjectCommand command) {
        apply(new TodosRemovedFromProjectEvent(command.getProjectId(), command.getTodoId()));
    }

    public ProjectAggregate() {
    }

    public ProjectAggregate(String id, String name) {
        super(id, name);
    }

    @EventSourcingHandler
    protected void on(ProjectCreatedEvent event) {
        setId(event.getId());
        setName(event.getName());
        this.aggregateIdentifier = event.getId();
    }

    @EventSourcingHandler
    protected void on(TodosAddedToProjectEvent event) {
        this.addTodoIds(event.getTodoIds());
    }

    @EventSourcingHandler
    protected void on(TodosRemovedFromProjectEvent event) {
        this.removeTodoIds(event.getTodoIds());
    }

    public String getAggregateIdentifier() {
        return aggregateIdentifier;
    }
}
