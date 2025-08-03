package com.jtd.order.controller;

import java.nio.charset.StandardCharsets;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jtd.logger.AuditLogger;
@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
public class OrderController {
	//private static final AuditLogger auditLogger = AuditLoggerFactory.getLogger(OrderController.class);

	@Autowired
	private AuditLogger auditLogger;
	@Autowired
	private OrderService servcie ;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${kafka.topic.order-created}")
	private String orderCreatedTopic;

	@PostMapping("/place")
	public String placeOrder(@RequestBody String order) {
		//String requestId = UUID.randomUUID().toString();
		auditLogger.log("order-service", "OrderController.placeOrder", MDC.get("X-Request-Id"), "STARTED", "Inside placeOrder");
		//auditLogger.info("inside placeOrder");
		try {
			System.out.println("gggggggg=>"+MDC.get("X-Request-Id").getBytes(StandardCharsets.UTF_8));
			ProducerRecord<String, String> record = new ProducerRecord<>(orderCreatedTopic, "1234", order.toString());

			//auditLogger.log("order-service", "OrderController.placeOrder", record.headers().lastHeader("X-Request-Id").toString(), "SUCCESScsacac", "Exits");

			record.headers().add("X-Request-Id", MDC.get("X-Request-Id").getBytes(StandardCharsets.UTF_8));
			kafkaTemplate.send(record);
			auditLogger.log("order-service", "OrderController.placeOrder", MDC.get("X-Request-Id"), "SUCCESS", "Exits");
			return "Order placed";
		} catch (Exception e) {
			auditLogger.log("order-service", "OrderController.placeOrder", MDC.get("X-Request-Id"), "FAILED", e.getMessage());
			throw e;
		}
	}

	@GetMapping
	public String getOrder() {
		System.out.println();
		//String requestId = UUID.randomUUID().toString();
		auditLogger.log("order-service", "OrderController.getOrder", MDC.get("X-Request-Id"), "STARTED", "Inside placeOrder");
		//auditLogger.info("inside getOrder");
		try {

			servcie.serviceMethod();
			//	auditLogger.info("Order Received");

			auditLogger.log("order-service", "OrderController.getOrder", MDC.get("X-Request-Id"), "SUCCESS", "logged Successfully");
			return "Order Received";
		} catch (Exception e) {
			auditLogger.log("order-service", "OrderController.getOrder", MDC.get("requestId"), "FAILED", e.getMessage());
			throw e;
		}
	}
}
