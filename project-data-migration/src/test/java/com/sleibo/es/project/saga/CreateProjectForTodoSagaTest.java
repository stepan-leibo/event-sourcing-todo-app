package com.sleibo.es.project.saga;

import com.sleibo.es.client.ProjectServiceClient;
import com.sleibo.es.domain.Project;
import com.sleibo.es.events.AddTodosToProjectCommand;
import com.sleibo.es.events.CreateProjectCommand;
import com.sleibo.es.events.TodoCreatedEvent;
import com.sleibo.es.events.TodosAddedToProjectEvent;
import com.sleibo.es.project.ProjectDataMigrationTestConfig;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.test.matchers.Matchers;
import org.axonframework.test.saga.SagaTestFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = CreateProjectForTodoSaga.class)
@RunWith(SpringRunner.class)
@Import(ProjectDataMigrationTestConfig.class)
public class CreateProjectForTodoSagaTest {

    private SagaTestFixture fixture;

    @Autowired
    private ProjectServiceClient projectClient;

    @Before
    public void before() {
        fixture = new SagaTestFixture<>(CreateProjectForTodoSaga.class);
        // a way to register spring bean with axon saga test fixture
        fixture.registerResource(projectClient);
    }

    @Test
    public void testCreateSaga() {
        given(projectClient.getProjects()).willReturn(new ArrayList<>());
        Predicate<List<? super CommandMessage<?>>> createProjectDispatched = (list) -> {
            assertEquals(1, list.size());
            Object payload = ((GenericCommandMessage) list.get(0)).getPayload();
            assertTrue(payload instanceof CreateProjectCommand);
            assertEquals("4 characters", ((CreateProjectCommand) payload).getText());

            return true;
        };
        fixture.givenNoPriorActivity()
               .whenPublishingA(new TodoCreatedEvent(UUID.randomUUID().toString(), "todo"))
               .expectActiveSagas(1)
               .expectDispatchedCommandsMatching(Matchers.matches(createProjectDispatched));
    }

    @Test
    public void testCreateSagaForExistingProject() {
        String projectId = UUID.randomUUID().toString();
        String todoId = UUID.randomUUID().toString();
        given(projectClient.getProjects()).willReturn(Arrays.asList(new Project(projectId, "4 characters")));

        Predicate<List<? super CommandMessage<?>>> matcher = (list) -> {
            assertEquals(1, list.size());
            Object payload = ((GenericCommandMessage) list.get(0)).getPayload();
            assertTrue(payload instanceof AddTodosToProjectCommand);
            assertEquals(projectId, ((AddTodosToProjectCommand) payload).getProjectId());
            assertEquals(Collections.singletonList(UUID.fromString(todoId)), ((AddTodosToProjectCommand) payload).getTodoIds());

            return true;
        };
        fixture.givenNoPriorActivity()
               .whenPublishingA(new TodoCreatedEvent(todoId, "todo"))
               .expectActiveSagas(1)
               .expectDispatchedCommandsMatching(Matchers.matches(matcher));
    }

    @Test
    public void testSagaEnds() {
        String projectId = UUID.randomUUID().toString();
        String todoId = UUID.randomUUID().toString();
        given(projectClient.getProjects()).willReturn(Arrays.asList(new Project(projectId, "4 characters")));
        fixture.givenAPublished(new TodoCreatedEvent(todoId, "todo"))
               .whenPublishingA(new TodosAddedToProjectEvent(projectId, Collections.singleton(UUID.fromString(todoId))))
               .expectNoDispatchedCommands()
               .expectActiveSagas(0);
    }
}
