package com.sleibo.es.notifications;

import com.rabbitmq.client.Channel;
import com.sleibo.es.notifications.handler.NotificationsEventHandler;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class MessagingConfig {
    @Autowired

    @Bean
    public Exchange eventsExchange() {
        // fanout is a way of routing messages
        // events is AWS concept of SNS
        return ExchangeBuilder.fanoutExchange("events").build();
    }

    @Bean
    public Queue participantsEventsQueue() {
        return QueueBuilder.durable("notifications-events").build();
    }

    @Bean
    public Binding participantsEventsBinding() {
        return BindingBuilder.bind(participantsEventsQueue()).to(eventsExchange()).with("*").noargs();
    }

    @Autowired
    public void configure(AmqpAdmin admin) {
        admin.declareExchange(eventsExchange());
        admin.declareQueue(participantsEventsQueue());
        admin.declareBinding(participantsEventsBinding());
    }

    @Bean
    public SpringAMQPMessageSource todoEvents(Serializer serializer) {
        return new SpringAMQPMessageSource(serializer) {
            // every service should have it's own queue
            @RabbitListener(queues = "notifications-events", exclusive = true)
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                super.onMessage(message, channel);
            }
        };
    }

    @Autowired
    private NotificationsEventHandler notificationsEventHandler;

    @Autowired
    // subscribe "notifications" processing group on message source
    public void configure(EventHandlingConfiguration config, SpringAMQPMessageSource messageSource) {
        config.registerSubscribingEventProcessor("notifications", c -> messageSource);
    }
}
