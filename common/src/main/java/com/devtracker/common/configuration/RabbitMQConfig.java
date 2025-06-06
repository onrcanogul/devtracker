package com.devtracker.common.configuration;

import com.devtracker.common.constant.RabbitMQConstants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //Dead Letter Queue
    @Bean
    public TopicExchange dlqExchange() {
        return new TopicExchange(RabbitMQConstants.DLQ_EXCHANGE);
    }
    @Bean
    public Queue dlqQueue() {
        return QueueBuilder.durable(RabbitMQConstants.DLQ_QUEUE).build();
    }
    @Bean
    public Binding dlqBinding() {
        return BindingBuilder
                .bind(dlqQueue())
                .to(dlqExchange())
                .with(RabbitMQConstants.DLQ_ROUTING_KEY);
    }
}
