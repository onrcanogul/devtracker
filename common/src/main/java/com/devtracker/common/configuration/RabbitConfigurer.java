package com.devtracker.common.configuration;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;


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
        factory.setAdviceChain(RetryInterceptorBuilder.stateless()
                .maxAttempts(maxAttempts)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build());
        return factory;
    }


}
