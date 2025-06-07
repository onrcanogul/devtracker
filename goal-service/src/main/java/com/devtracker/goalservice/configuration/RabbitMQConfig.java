package com.devtracker.goalservice.configuration;

import com.devtracker.common.configuration.RabbitConfigurer;
import com.devtracker.common.constant.RabbitMQConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory factory) {
        return RabbitConfigurer.rabbitListenerContainerFactory(factory, AcknowledgeMode.MANUAL, 3);
    }

    // ---------- Commit ----------
    @Bean
    public TopicExchange commitExchange() {
        return new TopicExchange(RabbitMQConstants.COMMIT_EXCHANGE);
    }

    @Bean
    public Queue commitCreatedQueue() {
        return buildDurableQueue(RabbitMQConstants.COMMIT_QUEUE);
    }

    @Bean
    public Binding commitCreateBinding(Queue commitCreatedQueue, TopicExchange commitExchange) {
        return BindingBuilder.bind(commitCreatedQueue).to(commitExchange).with(RabbitMQConstants.COMMIT_CREATED_ROUTING_KEY);
    }

    // ---------- Log ----------
    @Bean
    public TopicExchange logExchange() {
        return new TopicExchange(RabbitMQConstants.LOG_EXCHANGE);
    }

    @Bean
    public Queue logQueue() {
        return buildDurableQueue(RabbitMQConstants.LOG_QUEUE);
    }

    @Bean
    public Binding logCreateBinding(Queue logQueue, TopicExchange logExchange) {
        return BindingBuilder.bind(logQueue).to(logExchange).with(RabbitMQConstants.LOG_CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding logGoalProgressEvaluationBinding(Queue logQueue, TopicExchange logExchange){
        return BindingBuilder.bind(logQueue).to(logExchange).with(RabbitMQConstants.LOG_GOAL_PROGRESS_EVALUATION_ROUTING_KEY);
    }

    // ---------- Helper ----------
    private Queue buildDurableQueue(String queueName) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", RabbitMQConstants.DLQ_EXCHANGE);
        args.put("x-dead-letter-routing-key", RabbitMQConstants.DLQ_ROUTING_KEY);
        return new Queue(queueName, true, false, true, args);
    }

}
