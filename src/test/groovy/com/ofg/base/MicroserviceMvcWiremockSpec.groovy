package com.ofg.base

import com.ofg.infrastructure.base.MvcWiremockIntegrationSpec
import org.springframework.cloud.sleuth.instrument.web.TraceFilter
import com.ofg.infrastructure.web.correlationid.HeadersSettingFilter
import com.ofg.twitter.Application
import org.springframework.beans.factory.annotation.Autowire
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.trace.Trace
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder

@ContextConfiguration(classes = [Application], loader = SpringApplicationContextLoader)
class MicroserviceMvcWiremockSpec extends MvcWiremockIntegrationSpec {

    @Autowired Trace trace

    @Override
    protected void configureMockMvcBuilder(ConfigurableMockMvcBuilder mockMvcBuilder) {
        super.configureMockMvcBuilder(mockMvcBuilder)
        mockMvcBuilder.addFilter(new HeadersSettingFilter(), new TraceFilter(trace))
    }
}
