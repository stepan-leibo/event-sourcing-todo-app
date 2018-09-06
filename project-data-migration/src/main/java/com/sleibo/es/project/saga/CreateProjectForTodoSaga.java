package com.sleibo.es.project.saga;

import com.sleibo.es.client.ProjectServiceClient;
import com.sleibo.es.domain.Project;
import com.sleibo.es.events.AddTodosToProjectCommand;
import com.sleibo.es.events.CreateProjectCommand;
import com.sleibo.es.events.ProjectCreatedEvent;
import com.sleibo.es.events.TodoCreatedEvent;
import com.sleibo.es.events.TodosAddedToProjectEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaLifecycle;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Saga(configurationBean = "createProjectForTodoSagaConfigBean")
public class CreateProjectForTodoSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient ProjectServiceClient projectClient;

    private List<UUID> todoIds = new ArrayList<>();
    private String projectId;


    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void on(TodoCreatedEvent event) {
        todoIds.add(UUID.fromString(event.getId()));
        String projectName = getProjectNameFromTodoText(event.getText());
        Optional<Project> project = getProject(projectName);
        if (!project.isPresent()) {
            projectId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith("projectId", projectId);
            commandGateway.send(new CreateProjectCommand(projectId, projectName));
        } else {
            projectId = project.get().getId();
            SagaLifecycle.associateWith("projectId", projectId);
            commandGateway.send(new AddTodosToProjectCommand(projectId, todoIds));
        }
    }

    @SagaEventHandler(associationProperty = "id", keyName = "projectId")
    public void on(ProjectCreatedEvent event) {
        // can use event.getId() instead of projectId
        commandGateway.send(new AddTodosToProjectCommand(projectId, todoIds));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "projectId")
    public void on(TodosAddedToProjectEvent event) {
    }

    private synchronized Optional<Project> getProject(String projectName) {
        List<Project> projects = projectClient.getProjects();
        return projects.stream().filter(item -> item.getName().equals(projectName)).findAny();
    }

    private String getProjectNameFromTodoText(String text) {
        return text.length() + " characters";
    }

//    private String getProjectNameFromTodoText(String text) {
//        String[] words = text.split("\\s+");
//
//        return words.length + " word" + (words.length == 1 ? "" : "s");
//    }
}
