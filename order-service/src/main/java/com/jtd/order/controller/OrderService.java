package com.jtd.order.controller;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtd.logger.AuditLogger;
@Service
public class OrderService {
	@Autowired
	private AuditLogger auditLogger;
	public void serviceMethod() {
		auditLogger.log("order-service", "OrderService.serviceMethod", MDC.get("X-Request-Id"), "STARTED", "Inside serviceMethod");
	}
}
