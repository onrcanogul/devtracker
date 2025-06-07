package com.devtracker.goalservice.configuration;

import com.devtracker.common.configuration.RabbitConfigurer;
import com.devtracker.goalservice.messaging.MetadataHeaderProcessor;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitTemplateConfig {

    private final RabbitTemplate rabbitTemplate;
    private final MetadataHeaderProcessor metadataHeaderProcessor;

    @PostConstruct
    public void setupTemplate() {
        RabbitConfigurer.configure(rabbitTemplate, metadataHeaderProcessor);
    }
}
