package com.ofg.base

import com.ofg.infrastructure.base.MvcWiremockIntegrationSpec
import com.ofg.infrastructure.web.correlationid.HeadersSettingFilter
import com.ofg.twitter.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.cloud.sleuth.instrument.web.TraceFilter
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder

@ContextConfiguration(classes = [Application], loader = SpringApplicationContextLoader)
class MicroserviceMvcWiremockSpec extends MvcWiremockIntegrationSpec {

    @Autowired TraceFilter traceFilter

    @Override
    protected void configureMockMvcBuilder(ConfigurableMockMvcBuilder mockMvcBuilder) {
        super.configureMockMvcBuilder(mockMvcBuilder)
        mockMvcBuilder.addFilters(new HeadersSettingFilter(), traceFilter)
    }
}
