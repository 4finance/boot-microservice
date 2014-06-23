package com.ofg.infrastructure.filter
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.slf4j.MDC
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import static com.ofg.infrastructure.filter.CorrelationIdHolder.CORRELATION_ID_HEADER

@TypeChecked
@Slf4j
//inspired by http://taidevcouk.wordpress.com/2014/05/26/implementing-correlation-ids-in-spring-boot/
class CorrelationIdFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!isAsyncStarted(request)) {
            setupCorrelationId(request, response)
        }
        try {
            filterChain.doFilter(request, response)
        } finally {
            if (!isAsyncStarted(request)) {
                MDC.remove(CorrelationIdHolder.CORRELATION_ID_HEADER)
            }
        }
    }

    private void setupCorrelationId(HttpServletRequest request, HttpServletResponse response) {
        String correlationId = request.getHeader(CorrelationIdHolder.CORRELATION_ID_HEADER)
        correlationId = createNewCorrIdIfNecessary(correlationId)
        CorrelationIdHolder.setId(correlationId) //so you can use it in your async calls
        MDC.put(CorrelationIdHolder.CORRELATION_ID_HEADER, correlationId) //so it goes into logback logging
        response.addHeader(CORRELATION_ID_HEADER, correlationId) //so that it is returned back
    }

    private String createNewCorrIdIfNecessary(String currentCorrId) {
        if (currentCorrId == null) {
            currentCorrId = UUID.randomUUID().toString()
            log.info("No correlationId found in Header. Generated : " + currentCorrId)
        } else {
            log.info("Found correlationId in Header : " + currentCorrId)
        }
        return currentCorrId
    }
}