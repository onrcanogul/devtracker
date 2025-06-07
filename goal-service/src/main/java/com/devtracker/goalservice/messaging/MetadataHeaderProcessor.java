package com.devtracker.goalservice.messaging;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MetadataHeaderProcessor implements MessagePostProcessor {
    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        var props = message.getMessageProperties();

        if(!props.getHeaders().containsKey("correlationId")) {
            props.setHeader("correlationId", UUID.randomUUID().toString());
        }

        if(!props.getHeaders().containsKey("retryCount")) {
            props.setHeader("retryCount", 0);
        }

        if(!props.getHeaders().containsKey("timestamp")) {
            props.setHeader("timestamp", System.currentTimeMillis());
        }

        return message;
    }
}
