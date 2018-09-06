package com.sleibo.es.project;

import com.sleibo.es.client.ProjectServiceClient;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectDataMigrationTestConfig {

    @MockBean
    private ProjectServiceClient projectClient;
}
