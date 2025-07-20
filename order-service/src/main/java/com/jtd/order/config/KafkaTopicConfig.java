package com.jtd.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.order-created}")
    private String orderCreatedTopic;

    @Value("${kafka.topic.order-cancelled}")
    private String orderCancelledTopic;

    @Value("${kafka.topic.inventory-updated}")
    private String inventoryUpdatedTopic;

    @Bean
    public NewTopic orderCreated() {
        return TopicBuilder.name(orderCreatedTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic orderCancelled() {
        return TopicBuilder.name(orderCancelledTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic inventoryUpdated() {
        return TopicBuilder.name(inventoryUpdatedTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
