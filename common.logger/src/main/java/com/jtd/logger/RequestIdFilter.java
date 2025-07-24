package com.jtd.logger;


import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // ✅ So it’s auto-registered OR use config bean
public class RequestIdFilter implements Filter {

    private static final String REQUEST_ID_HEADER = "X-Request-Id";
    private static final String REQUEST_ID_KEY = "requestId";
    private static final Logger log = LoggerFactory.getLogger(RequestIdFilter.class);
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestId = httpRequest.getHeader(REQUEST_ID_HEADER);
        
        if (requestId == null || requestId.isBlank()) {
            requestId = UUID.randomUUID().toString();
        }

        try {
            MDC.put(REQUEST_ID_KEY, requestId);
            log.debug("RequestIdFilter set requestId: {}", requestId);
            
            if (response instanceof HttpServletResponse) {
                ((HttpServletResponse) response).addHeader(REQUEST_ID_HEADER, requestId);
            }
            
            chain.doFilter(request, response);
        } finally {
            // Only clear if not async
            if (!request.isAsyncStarted()) {
                MDC.remove(REQUEST_ID_KEY);
                log.debug("RequestIdFilter cleared requestId");
            }
        }
    }
}

