package com.ofg.base

import com.ofg.infrastructure.base.MvcIntegrationSpec
import com.ofg.microservice.infrastructure.correlationid.CorrelationIdFilter
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import static com.ofg.microservice.config.Profiles.TEST

@ContextConfiguration(classes = [ServiceDiscoveryStubbingApplicationConfiguration], loader = SpringApplicationContextLoader)
@ActiveProfiles(TEST)
class MicroserviceMvcIntegrationSpec extends MvcIntegrationSpec {
    
    def setup() {
        mockMvc = MockMvcBuilders.
                webAppContextSetup(webApplicationContext).
                addFilter(new CorrelationIdFilter()).
                build()
    }
}
