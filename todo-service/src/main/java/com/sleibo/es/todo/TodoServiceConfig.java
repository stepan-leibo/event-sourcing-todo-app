package com.sleibo.es.todo;

import com.sleibo.es.config.MongoEventStoreConfig;
import org.axonframework.config.EventHandlingConfiguration;
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
public class TodoServiceConfig {

    @Autowired
    public void configure(EventHandlingConfiguration config) {
        config.usingTrackingProcessors();
    }

    @Bean
    public TokenStore mongoTokenStore(MongoTemplate mongoTemplate, Serializer serializer) {
        return new MongoTokenStore(mongoTemplate, serializer);
    }

}
