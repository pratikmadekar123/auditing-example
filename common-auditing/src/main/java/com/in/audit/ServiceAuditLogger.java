package com.in.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceAuditLogger implements AuditLogger {
    private final Logger logger;
    private final String serviceName;

    public ServiceAuditLogger(String serviceName) {
        this.serviceName = serviceName;
        this.logger = LoggerFactory.getLogger("AUDIT_LOGGER_" + serviceName);
    }

    private String formatMessage(String message) {
        return String.format("[%s] %s", serviceName, message);
    }

    // TRACE level
    @Override
    public void trace(String message) {
        if (isTraceEnabled()) {
            logger.trace(formatMessage(message));
        }
    }

    @Override
    public void trace(String format, Object... args) {
        if (isTraceEnabled()) {
            logger.trace(formatMessage(String.format(format, args)));
        }
    }

    // DEBUG level
    @Override
    public void debug(String message) {
        if (isDebugEnabled()) {
            logger.debug(formatMessage(message));
        }
    }

    @Override
    public void debug(String format, Object... args) {
        if (isDebugEnabled()) {
            logger.debug(formatMessage(String.format(format, args)));
        }
    }

    // INFO level
    @Override
    public void info(String message) {
        if (isInfoEnabled()) {
            logger.info(formatMessage(message));
        }
    }

    @Override
    public void info(String format, Object... args) {
        if (isInfoEnabled()) {
            logger.info(formatMessage(String.format(format, args)));
        }
    }

    // WARN level
    @Override
    public void warn(String message) {
        if (isWarnEnabled()) {
            logger.warn(formatMessage(message));
        }
    }

    @Override
    public void warn(String format, Object... args) {
        if (isWarnEnabled()) {
            logger.warn(formatMessage(String.format(format, args)));
        }
    }

    @Override
    public void warn(String message, Throwable throwable) {
        if (isWarnEnabled()) {
            logger.warn(formatMessage(message), throwable);
        }
    }

    // ERROR level
    @Override
    public void error(String message) {
        if (isErrorEnabled()) {
            logger.error(formatMessage(message));
        }
    }

    @Override
    public void error(String format, Object... args) {
        if (isErrorEnabled()) {
            logger.error(formatMessage(String.format(format, args)));
        }
    }

    @Override
    public void error(String message, Throwable throwable) {
        if (isErrorEnabled()) {
            logger.error(formatMessage(message), throwable);
        }
    }

    // FATAL level (mapped to ERROR in SLF4J as it doesn't have FATAL)
    @Override
    public void fatal(String message) {
        if (isFatalEnabled()) {
            logger.error("[FATAL] " + formatMessage(message));
        }
    }

    @Override
    public void fatal(String format, Object... args) {
        if (isFatalEnabled()) {
            logger.error("[FATAL] " + formatMessage(String.format(format, args)));
        }
    }

    @Override
    public void fatal(String message, Throwable throwable) {
        if (isFatalEnabled()) {
            logger.error("[FATAL] " + formatMessage(message), throwable);
        }
    }

    // Level check methods
    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public boolean isFatalEnabled() {
        return logger.isErrorEnabled(); // FATAL maps to ERROR
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }
}
