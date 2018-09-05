package com.sleibo.es.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(PublicApiAppConfig.class)
public class PublicApiApp {
    public static void main(String[] args) {
        SpringApplication.run(PublicApiApp.class, args);
    }
}
