package com.devtracker.common.constant;

public class RabbitMQConstants {
    //Dead Letter Queue
    public static final String DLQ_QUEUE = "q.dlq";
    public static final String DLQ_EXCHANGE = "ex.dlq";
    public static final String DLQ_ROUTING_KEY = "routing-key.dlq";

    //Commit Events
    public static final String COMMIT_QUEUE = "q.commit";
    public static final String COMMIT_EXCHANGE = "ex.commit";
    public static final String COMMIT_CREATED_ROUTING_KEY = "routing-key.commit";

    //Log Events
}
