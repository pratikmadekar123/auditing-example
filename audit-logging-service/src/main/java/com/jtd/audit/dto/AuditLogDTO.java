package com.jtd.audit.dto;

import java.util.UUID;

import lombok.Data;
@Data
public class AuditLogDTO {

		private String id = UUID.randomUUID().toString();
	    private String timestamp;
	    private String service;
	    private String method;
	    private String requestId;
	    private String status;
	    private String error;
}
