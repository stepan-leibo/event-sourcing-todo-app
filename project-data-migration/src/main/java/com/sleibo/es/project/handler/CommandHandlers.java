package com.sleibo.es.project.handler;

import com.sleibo.es.client.EventServiceClient;
import com.sleibo.es.domain.Project;
import com.sleibo.es.events.AddTodosToProjectCommand;
import com.sleibo.es.events.CreateProjectCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.messaging.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
// FIXME: dirty hack as I had no time to make distributed command bus work
public class CommandHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandHandler.class);

    private final EventServiceClient eventServiceClient = new EventServiceClient();

    @CommandHandler
    public void on(CreateProjectCommand command) {
        eventServiceClient.createProject(new Project(command.getId(), command.getText()));
    }

    @CommandHandler
    public void on(AddTodosToProjectCommand command, MetaData metaData) {
        LOGGER.info("Meta data " + metaData);
        eventServiceClient.addTodosToProject(UUID.fromString(command.getProjectId()), command.getTodoIds());
    }
}
