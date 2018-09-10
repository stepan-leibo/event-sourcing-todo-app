package com.sleibo.es.event;

import com.sleibo.es.config.MongoEventStoreConfig;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import(MongoEventStoreConfig.class)
class EventServiceConfig {

    // MESSAGING

    @Bean
    public Exchange eventsExchange() {
        // fanout is a way of routing messages
        // events is AWS concept of SNS
        return ExchangeBuilder.fanoutExchange("events").build();
    }

    @Autowired
    public void configure(AmqpAdmin admin) {
        admin.declareExchange(eventsExchange());
    }
}