package com.sleibo.es.stats.service;

import com.sleibo.es.domain.Project;
import com.sleibo.es.domain.Todo;
import com.sleibo.es.events.ProjectCreatedEvent;
import com.sleibo.es.events.TodoCreatedEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class StatsCollectorService {
    private final Map<Long, AtomicInteger> stats = new ConcurrentHashMap<>();

    public void process(TodoCreatedEvent todo, Long epochSecond) {
        initializeStats(epochSecond);

        stats.get(getKey(epochSecond)).addAndGet(1);
    }

    private void initializeStats(Long epochSecond) {
        if (!stats.containsKey(getKey(epochSecond))) {
            stats.put(getKey(epochSecond), new AtomicInteger());
        }
    }

    public Map<Long, AtomicInteger> getStats() {
        return stats;
    }

    private Long getKey(Long epochSecond) {
        return epochSecond / 60;
    }
}
