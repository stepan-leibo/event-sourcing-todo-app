package com.sleibo.es.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ProjectDataMigrationConfig.class)
public class ProjectDataMigrationApp {
    public static void main(String[] args) {
        SpringApplication.run(ProjectDataMigrationApp.class, args);
    }

    // implements import org.springframework.boot.CommandLineRunner
//    public static void main(String[] args) {
//        SpringApplication app = new SpringApplication(ProjectDataMigrationApp.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.run( args);
//
//    }
//
//    public void run(String... args) {
//    }
}
