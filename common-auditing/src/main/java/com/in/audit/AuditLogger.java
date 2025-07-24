package com.in.audit;


public interface AuditLogger {
    // Service identification
    String getServiceName();
    //String getApplicationName();
    // TRACE level
    void trace(String message);
    void trace(String format, Object... args);
    
    // DEBUG level
    void debug(String message);
    void debug(String format, Object... args);
    
    // INFO level (standard audit logs)
    void info(String message);
    void info(String format, Object... args);
    
    // WARN level
    void warn(String message);
    void warn(String format, Object... args);
    void warn(String message, Throwable throwable);
    
    // ERROR level
    void error(String message);
    void error(String format, Object... args);
    void error(String message, Throwable throwable);
    
    // FATAL level
    void fatal(String message);
    void fatal(String format, Object... args);
    void fatal(String message, Throwable throwable);
    
    // Utility methods
    boolean isTraceEnabled();
    boolean isDebugEnabled();
    boolean isInfoEnabled();
    boolean isWarnEnabled();
    boolean isErrorEnabled();
    boolean isFatalEnabled();
}