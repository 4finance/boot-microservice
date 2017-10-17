package com.ofg.base

import com.ofg.infrastructure.base.IntegrationSpec
import com.ofg.twitter.Application
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [Application])
class MicroserviceIntegrationSpec extends IntegrationSpec {
}
