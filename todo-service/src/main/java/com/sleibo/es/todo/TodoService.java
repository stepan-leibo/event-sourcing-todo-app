package com.sleibo.es.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TodoServiceConfig.class)
public class TodoService {
    public static void main(String[] args) {
        SpringApplication.run(TodoService.class, args);
    }
}
