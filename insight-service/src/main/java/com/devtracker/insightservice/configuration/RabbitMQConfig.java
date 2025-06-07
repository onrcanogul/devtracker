package com.devtracker.insightservice.configuration;

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
    @Bean
    public TopicExchange insightExchange() {
        return new TopicExchange(RabbitMQConstants.INSIGHT_EXCHANGE);
    }
    @Bean
    public Queue insightQueue() { return buildDurableQueue(RabbitMQConstants.INSIGHT_QUEUE); }
    @Bean
    public Binding insightAnalyzeCreatedBinding(Queue insightQueue, TopicExchange insightExchange) {
        return BindingBuilder.bind(insightQueue).to(insightExchange).with(RabbitMQConstants.INSIGHT_ANALYZE_CREATED_ROUTING_KEY);
    }

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


    private Queue buildDurableQueue(String queueName) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", RabbitMQConstants.DLQ_EXCHANGE);
        args.put("x-dead-letter-routing-key", RabbitMQConstants.DLQ_ROUTING_KEY);
        return new Queue(queueName, true, false, true, args);
    }
}
