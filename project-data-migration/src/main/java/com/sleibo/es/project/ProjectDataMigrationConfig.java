package com.sleibo.es.project;

import com.sleibo.es.client.ProjectServiceClient;
import com.sleibo.es.config.MongoEventStoreConfig;
import com.sleibo.es.project.saga.CreateProjectForTodoSaga;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import(MongoEventStoreConfig.class)
public class ProjectDataMigrationConfig {
    @Autowired
    public void configure(EventHandlingConfiguration config) {
        config.usingTrackingProcessors();
    }

    @Bean
    public TokenStore mongoTokenStore(MongoTemplate mongoTemplate, Serializer serializer) {
        return new MongoTokenStore(mongoTemplate, serializer);
    }

    @Bean
    public ProjectServiceClient projectServiceClient() {
        return new ProjectServiceClient();
    }

    // SAGA

    @Bean
    public SagaConfiguration<CreateProjectForTodoSaga> createProjectForTodoSagaConfigBean() {
        // saga is executed in it's own thread
        return SagaConfiguration.trackingSagaManager(CreateProjectForTodoSaga.class);
    }
}
