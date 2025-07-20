package com.jtd.audit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jtd.audit.dto.AuditLogDTO;

@RestController
@RequestMapping("/audit")
public class AuditLogController {

	  @PostMapping("/log")
	    public void log(@RequestBody AuditLogDTO log) {
	       System.out.println(log.getRequestId());
	       System.out.println(log.getService());
	       System.out.println(log.getMethod());
	       System.out.println(log.getTimestamp());
	       System.out.println(log.getStatus());
	       
	    }
	  
	  @GetMapping("/msg")
	    public ResponseEntity<?> getMessage() {
		  
		  return ResponseEntity.ok().build();
	  }
}
