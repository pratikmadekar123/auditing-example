package com.in.report.config;

import org.springframework.web.filter.OncePerRequestFilter;

public class RequestIdFilter{
//@Component
//public class RequestIdFilter extends OncePerRequestFilter {
/*
    public static final String REQUEST_ID_HEADER = "X-Request-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String requestId = UUID.randomUUID().toString();

        // Set it to request attribute
        request.setAttribute(REQUEST_ID_HEADER, requestId);

        // Optional: Add it to response header for tracing in client
        response.setHeader(REQUEST_ID_HEADER, requestId);

        // Optional: Store it in MDC for logging
        MDC.put(REQUEST_ID_HEADER, requestId);

        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(REQUEST_ID_HEADER);
        }
    }
    */
}
