package com.sleibo.es.event;

import com.sleibo.es.config.MongoEventStoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import(MongoEventStoreConfig.class)
class EventServiceConfig {

}