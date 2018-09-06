package com.sleibo.es.project.handler;

import com.sleibo.es.events.ProjectCreatedEvent;
import com.sleibo.es.events.TodosAddedToProjectEvent;
import com.sleibo.es.events.TodosRemovedFromProjectEvent;
import com.sleibo.es.project.domain.ProjectEntity;
import com.sleibo.es.project.persistence.ProjectRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("project")
public class ProjectEventHandler {
    @Autowired
    private ProjectRepository projectRepository;

    @EventHandler
    @AllowReplay
    public void on(ProjectCreatedEvent event) {
        if (!projectRepository.existsByName(event.getName())) {
            projectRepository.save(new ProjectEntity(event.getId(), event.getName()));
        }
    }

    @EventHandler
    @AllowReplay
    public void on(TodosAddedToProjectEvent event) {
        ProjectEntity project = projectRepository.getOne(event.getProjectId());
        project.addTodoIds(event.getTodoIds());
        projectRepository.save(project);
    }

    @EventHandler
    @AllowReplay
    public void on(TodosRemovedFromProjectEvent event) {
        ProjectEntity project = projectRepository.getOne(event.getProjectId());
        project.removeTodoIds(event.getTodoIds());
        projectRepository.save(project);}
}
