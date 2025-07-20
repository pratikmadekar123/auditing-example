package com.jtd.inventory.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.jtd.logger.AuditLogger;

@Service
public class InventoryConsumer {

    @Autowired
    private AuditLogger auditLogger;
    @Value("${kafka.topic.order-created}")
    private String orderCreatedTopic;

    @KafkaListener(topics = "${kafka.topic.order-created}", groupId = "inventory-group")
    public void consumeOrder(String message) {
        String requestId = UUID.randomUUID().toString();
        auditLogger.log("inventory-service", "InventoryConsumer.consumeOrder", requestId, "RECEIVED", message.toString());

        try {
            // Inventory update logic here...
            auditLogger.log("inventory-service", "InventoryConsumer.consumeOrder", requestId, "SUCCESS", null);
        } catch (Exception e) {
            auditLogger.log("inventory-service", "InventoryConsumer.consumeOrder", requestId, "FAILED", e.getMessage());
        }
    }
}

