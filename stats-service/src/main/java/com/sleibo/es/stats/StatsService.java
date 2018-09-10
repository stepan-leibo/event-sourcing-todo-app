package com.sleibo.es.stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(StatsServiceConfig.class)
public class StatsService {
    public static void main(String[] args) {
        SpringApplication.run(StatsService.class, args);
    }
}
