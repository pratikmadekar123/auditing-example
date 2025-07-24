package com.in.audit;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AuditLogConfigurator {
    private static final Map<String, Boolean> configuredServices = new HashMap<>();
    private static final String DEFAULT_CONFIG = "logback-template.xml";
    private static AuditConfig globalConfig = new AuditConfig();

    public static synchronized void configureForService(String serviceName) {
        configureForService(serviceName, globalConfig);
    }

    public static synchronized void configureForService(String serviceName, AuditConfig config) {
        if (configuredServices.containsKey(serviceName)) {
            return;
        }

        // Set properties for logback configuration
        System.setProperty("audit.service.name", serviceName);
        if (config.getLogDirectory() != null) {
            System.setProperty("audit.log.dir", config.getLogDirectory());
        }
        if (config.getLogPattern() != null) {
            System.setProperty("audit.log.pattern", config.getLogPattern());
        }

        try {
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            context.reset();
            
            InputStream configStream = AuditLogConfigurator.class.getClassLoader()
                .getResourceAsStream(DEFAULT_CONFIG);
            
            if (configStream != null) {
                configurator.doConfigure(configStream);
                configuredServices.put(serviceName, true);
            }
        } catch (Exception e) {
            System.err.println("Failed to configure audit logging for: " + serviceName);
            e.printStackTrace();
        }
    }

    public static void setGlobalConfig(AuditConfig config) {
        globalConfig = config;
    }
}