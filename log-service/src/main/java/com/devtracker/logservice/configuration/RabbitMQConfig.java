package com.devtracker.logservice.configuration;

import com.devtracker.common.constant.RabbitMQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
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
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(org.springframework.amqp.core.AcknowledgeMode.MANUAL);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAdviceChain(RetryInterceptorBuilder.stateless()
                .maxAttempts(3)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build());
        return factory;
    }
    @Bean
    public TopicExchange commitExchange() {
        return new TopicExchange(RabbitMQConstants.COMMIT_EXCHANGE);
    }
    @Bean
    public Queue commitCreatedQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", RabbitMQConstants.DLQ_EXCHANGE);
        args.put("x-dead-letter-routing-key", RabbitMQConstants.DLQ_ROUTING_KEY);
        return new Queue(RabbitMQConstants.COMMIT_QUEUE, true, false, true, args);
    }
    @Bean
    public Binding commitCreateBinding(Queue commitCreatedQueue, TopicExchange commitExchange) {
        return BindingBuilder.bind(commitCreatedQueue).to(commitExchange).with(RabbitMQConstants.COMMIT_CREATED_ROUTING_KEY);
    }
}
