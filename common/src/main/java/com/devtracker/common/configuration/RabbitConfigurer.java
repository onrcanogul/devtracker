package com.devtracker.common.configuration;

import com.devtracker.common.exception.BadRequestException;
import com.devtracker.common.exception.NotFoundException;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;

import java.util.HashMap;
import java.util.Map;


public class RabbitConfigurer {

    public static void configure(RabbitTemplate template, MessagePostProcessor processor) {
        template.setBeforePublishPostProcessors(processor);
    }


    public static SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            AcknowledgeMode acknowledgeMode,
            int maxAttempts) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(acknowledgeMode);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());


        Map<Class<? extends Throwable>, Boolean> retryableExceptions = new HashMap<>();
        retryableExceptions.put(NotFoundException.class, false);
        retryableExceptions.put(BadRequestException.class, false);

        RetryPolicy retryPolicy = new SimpleRetryPolicy(maxAttempts, retryableExceptions, true);

        factory.setAdviceChain(RetryInterceptorBuilder.stateless()
                .retryPolicy(retryPolicy)
                .recoverer(new RejectAndDontRequeueRecoverer()) // DLQ
                .build());

        return factory;
    }



}
