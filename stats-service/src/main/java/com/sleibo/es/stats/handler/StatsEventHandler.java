package com.sleibo.es.stats.handler;

import com.sleibo.es.events.ProjectCreatedEvent;
import com.sleibo.es.events.TodoCreatedEvent;
import com.sleibo.es.stats.service.StatsCollectorService;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
@ProcessingGroup("stats")
public class StatsEventHandler {
    @Autowired
    private StatsCollectorService statsCollectorService;

    @EventHandler
    @AllowReplay
    public void on(TodoCreatedEvent event, @Timestamp Instant instant) {
        statsCollectorService.process(event, instant.getEpochSecond());
    }
}
