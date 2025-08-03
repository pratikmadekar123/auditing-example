package com.jtd.inventory.contorller;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jtd.logger.AuditLogger;



@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private AuditLogger auditLogger;
	@GetMapping
	 public void consumeOrder() {
		auditLogger.log("inventory-service", "InventoryConsumer.consumeOrder", MDC.get("X-Request-Id"), "RECEIVED", null);
	
	    	try {
	    		auditLogger.log("inventory-service", "InventoryConsumer.consumeOrder", MDC.get("X-Request-Id"), "RECEIVED", null);
	
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	auditLogger.log("inventory-service", "InventoryConsumer.consumeOrder", MDC.get("X-Request-Id"), "RECEIVED", null);
	    	
	    }
}
