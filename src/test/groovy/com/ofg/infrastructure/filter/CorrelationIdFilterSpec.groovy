package com.ofg.infrastructure.filter
import com.ofg.base.MvcIntegrationSpec
import groovy.transform.TypeChecked
import org.slf4j.MDC
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MvcResult

import static com.ofg.infrastructure.filter.CorrelationIdHolder.CORRELATION_ID_HEADER
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@TypeChecked
class CorrelationIdFilterSpec extends MvcIntegrationSpec {

    def "should create and return correlationId in HTTP header"() {
        when:
            MvcResult mvcResult = sendPingWithoutCorrelationId()

        then:
            getCorrelationIdFromResonseHeader(mvcResult) != null
    }

    def "when correlationId is sent, should not create a new one, but return the existing one instead"() {
        given:
            String passedCorrelationId = "passedCorId"

        when:
            MvcResult mvcResult = sendPingWithCorrelationId(passedCorrelationId)

        then:
            getCorrelationIdFromResonseHeader(mvcResult) == passedCorrelationId
    }

    def "should clean up MDC after the call"() {
        given:
            String passedCorrelationId = "passedCorId"

        when:
            sendPingWithCorrelationId(passedCorrelationId)

        then:
            MDC.get(CorrelationIdHolder.CORRELATION_ID_HEADER) == null
    }

    private MvcResult sendPingWithCorrelationId(String passedCorrelationId) {
        mockMvc.perform(get('/ping').accept(MediaType.TEXT_PLAIN).header(CORRELATION_ID_HEADER, passedCorrelationId)).andReturn()
    }

    private MvcResult sendPingWithoutCorrelationId() {
        mockMvc.perform(get('/ping').accept(MediaType.TEXT_PLAIN)).andReturn()
    }

    private String getCorrelationIdFromResonseHeader(MvcResult mvcResult) {
        mvcResult.getResponse().getHeader(CORRELATION_ID_HEADER)
    }
}
