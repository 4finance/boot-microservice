package com.ofg.base

import com.ofg.infrastructure.base.MvcWiremockIntegrationSpec
import com.ofg.twitter.Application
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [Application], loader = SpringApplicationContextLoader)
class MicroserviceMvcWiremockSpec extends MvcWiremockIntegrationSpec {
}
