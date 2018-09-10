package com.sleibo.es.stats.service;

import com.sleibo.es.events.TodoCreatedEvent;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
