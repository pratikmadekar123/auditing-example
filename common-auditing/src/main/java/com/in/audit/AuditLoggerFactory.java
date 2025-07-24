package com.in.audit;

public class AuditLoggerFactory {
    private static final String DEFAULT_SERVICE_NAME = "default-service";
    
    public static AuditLogger getLogger(String serviceName) {
        String resolvedName = resolveServiceName(serviceName);
        AuditLogConfigurator.configureForService(resolvedName);
        return new ServiceAuditLogger(resolvedName);
    }

    public static AuditLogger getLogger(Class<?> clazz) {
        return getLogger(clazz.getSimpleName());
    }

    private static String resolveServiceName(String requestedName) {
        // 1. Check system property (set by Spring Boot auto-config)
        String systemPropertyName = System.getProperty("spring.application.name");
        if (systemPropertyName != null && !systemPropertyName.isEmpty()) {
            return systemPropertyName;
        }
        
        // 2. Fall back to requested name or default
        return requestedName != null ? requestedName : DEFAULT_SERVICE_NAME;
    }

    public static void initialize(AuditConfig config) {
        AuditLogConfigurator.setGlobalConfig(config);
    }
}