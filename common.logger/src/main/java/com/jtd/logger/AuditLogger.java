package com.jtd.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuditLogger {

	@Value("${common.logger.audit-service-url}")
	private String AUDIT_SERVICE_URL;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//private static String requestId1 = UUID.randomUUID().toString();
	//private final String AUDIT_SERVICE_URL = "http://audit-logging-service/audit/log";

	public void log(String service, String method, String requestId, String status, String error) {
		AuditLogDTO log = new AuditLogDTO();
		log.setTimestamp(Instant.now().toString());
		log.setService(service);
		log.setMethod(method);
		log.setRequestId(requestId);
		log.setStatus(status);
		log.setError(error);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		
		HttpEntity<AuditLogDTO> entity = new HttpEntity<>(log, headers);
		try {
			restTemplate.postForObject(AUDIT_SERVICE_URL, entity, Void.class);
		} catch (Exception ex) {
			System.err.println("Audit logging failed: " + ex.getMessage());
		}
		
		// Write to local log file
        writeToLocalFile(log);
	}
	
	private void writeToLocalFile(AuditLogDTO log) {
        try {
            String basePath = "D:/" + log.getService(); // e.g., D:/order-service
            File dir = new File(basePath + "/logs");
            if (!dir.exists()) dir.mkdirs();

            String filename = basePath + "/logs/audit-log-"+log.getService()+".log";


            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                writer.write(formatLog(log));
                writer.newLine();
            }
        } catch (Exception e) {
            System.err.println("Failed to write local audit log: " + e.getMessage());
        }
    }

    private String formatLog(AuditLogDTO log) {
        return String.format("[%s] [%s] [%s] [%s] [%s] %s",
                log.getTimestamp(),
                log.getService(),
                log.getMethod(),
                log.getRequestId(),
                log.getStatus(),
                log.getError() == null ? "" : log.getError());
    }
}
