package com.sleibo.es.config;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.MongoFactory;
import org.axonframework.mongo.eventsourcing.eventstore.documentperevent.DocumentPerEventStorageStrategy;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableMongoRepositories
public class MongoEventStoreConfig {
    @Value("${mongodb.url:127.0.0.1}")
    private String mongoUrl;

    @Value("${mongodb.dbname:todoeventstore}")
    private String mongoDbName;

    @Bean
    public EventStorageEngine eventStore(Serializer serializer){
        return new MongoEventStorageEngine(
            serializer,null, axonMongoTemplate(), new DocumentPerEventStorageStrategy());
    }

    @Bean(name = "axonMongoTemplate")
    public MongoTemplate axonMongoTemplate() {
        MongoFactory mongoFactory = new MongoFactory();
        mongoFactory.setMongoAddresses(Collections.singletonList(new ServerAddress(mongoUrl)));
        MongoClient mongoClient = mongoFactory.createMongo();
        return new DefaultMongoTemplate(mongoClient, mongoDbName);
    }

    @Bean
    public MongoSagaStore sagaStore() {
        return new MongoSagaStore(axonMongoTemplate());
    }
}