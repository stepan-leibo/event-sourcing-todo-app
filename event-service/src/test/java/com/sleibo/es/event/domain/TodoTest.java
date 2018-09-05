package com.sleibo.es.event.domain;

import com.sleibo.es.events.CreateTodoCommand;
import com.sleibo.es.events.TodoCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

public class TodoTest {
    private FixtureConfiguration<TodoAggregate> fixture;

    @Before
    public void before() {
        fixture = new AggregateTestFixture<>(TodoAggregate.class);
    }

    @Test
    public void testTodoCreated() {
        fixture.givenNoPriorActivity()
               .when(new CreateTodoCommand("td1", "First todo"))
               .expectEvents(new TodoCreatedEvent("td1", "First todo"));
    }
}
