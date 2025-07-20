package com.jtd.order.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jtd.logger.AuditLogger;

@RestController
@RequestMapping("/orders")
public class OrderController {



    @Autowired
    private AuditLogger auditLogger;

    @PostMapping
    public String placeOrder(@RequestBody String order) {
        String requestId = UUID.randomUUID().toString();
        auditLogger.log("order-service", "OrderController.placeOrder", requestId, "STARTED", "Inside placeOrder");

        try {
        
            auditLogger.log("order-service", "OrderController.placeOrder", requestId, "SUCCESS", null);
            return "Order placed";
        } catch (Exception e) {
            auditLogger.log("order-service", "OrderController.placeOrder", requestId, "FAILED", e.getMessage());
            throw e;
        }
    }
    
    @GetMapping
    public String getOrder() {
    	System.out.println();
        String requestId = UUID.randomUUID().toString();
        auditLogger.log("order-service", "OrderController.getOrder", requestId, "STARTED", "Inside placeOrder");

        try {
        
            auditLogger.log("order-service", "OrderController.getOrder", requestId, "SUCCESS", "logged Successfully");
            return "Order Received";
        } catch (Exception e) {
            auditLogger.log("order-service", "OrderController.getOrder", requestId, "FAILED", e.getMessage());
            throw e;
        }
    }
}
