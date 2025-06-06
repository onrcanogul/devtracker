package com.devtracker.common.configuration;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitPublisherConfigurer {

    public static void configure(RabbitTemplate template, MessagePostProcessor processor) {
        template.setBeforePublishPostProcessors(processor);
    }
}
