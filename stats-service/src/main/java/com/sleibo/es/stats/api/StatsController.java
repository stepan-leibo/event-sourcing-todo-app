package com.sleibo.es.stats.api;

import com.sleibo.es.stats.service.StatsCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class StatsController {

    @Autowired
    private StatsCollectorService statsCollectorService;

    @GetMapping("/stats")
    public ResponseEntity<Map<Long, AtomicInteger>> getStats() {
        return ResponseEntity.ok(statsCollectorService.getStats());
    }
}
