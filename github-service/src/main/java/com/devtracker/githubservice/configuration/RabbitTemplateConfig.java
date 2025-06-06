package com.devtracker.githubservice.configuration;

import com.devtracker.common.configuration.RabbitPublisherConfigurer;
import com.devtracker.githubservice.messaging.MetadataHeaderProcessor;
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
        RabbitPublisherConfigurer.configure(rabbitTemplate, metadataHeaderProcessor);
    }
}
