package com.ofg.twitter.geb

import org.springframework.boot.test.IntegrationTest

@IntegrationTest("spring.profiles.active:dev")
class MicroserviceJsonHealthEndpointUISpec extends AcceptanceSwaggerUISpec {
}
