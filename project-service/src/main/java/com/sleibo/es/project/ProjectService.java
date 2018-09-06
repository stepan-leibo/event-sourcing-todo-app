package com.sleibo.es.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ProjectServiceConfig.class)
public class ProjectService {
    public static void main(String[] args) {
        SpringApplication.run(ProjectService.class, args);
    }
}
