package com.jtd.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jtd.logger.AuditLogger;

@Configuration
public class LoggerConfig {

    @Bean
    public AuditLogger auditLogger() {
        return new AuditLogger(); // constructor with no args
    }
}