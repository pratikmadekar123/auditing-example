package com.jtd.inventory.service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.jtd.logger.AuditLogger;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryConsumer {
	// private static final AuditLogger auditLogger = AuditLoggerFactory.getLogger(InventoryConsumer.class);
	@Autowired
	private AuditLogger auditLogger;
	@Value("${kafka.topic.order-created}")
	private String orderCreatedTopic;

	@KafkaListener(topics = "${kafka.topic.order-created}", groupId = "inventory-group")
	public void consumeOrder(ConsumerRecord<String, String> record) {
		String requestId = Optional.ofNullable(record.headers().lastHeader("X-Request-Id"))
				.map(header -> new String(header.value(), StandardCharsets.UTF_8))
				.orElse("UNKNOWN");

		MDC.put("X-Request-Id", requestId);
		log.info("X-Request-Id====>"+requestId);
		auditLogger.log("inventory-service", "InventoryConsumer.consumeOrder", requestId, "RECEIVED",record.value());
		try {
			// Log the requestId and message

			auditLogger.log("inventory-service", "InventoryConsumer.consumeOrder", requestId, "RECEIVED", record.value());
			// Your business logic here
			String message = record.value();
			// deserialize message if it's a JSON, etc.

		} finally {
			// Clean up MDC to avoid leaking into other threads
			MDC.clear();
		}

	}

}

