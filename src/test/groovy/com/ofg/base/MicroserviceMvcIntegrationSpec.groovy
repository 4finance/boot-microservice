package com.ofg.base

import com.ofg.infrastructure.base.MvcIntegrationSpec
import com.ofg.twitter.Application
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [Application])
class MicroserviceMvcIntegrationSpec extends MvcIntegrationSpec {
}
