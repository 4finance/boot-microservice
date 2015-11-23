package com.ofg.twitter.geb.microservicejson

import com.ofg.twitter.geb.HealthEndpointPageUISpec
import org.springframework.boot.test.IntegrationTest

@IntegrationTest("spring.profiles.active:dev")
class MicroserviceJsonHealthEndpointUISpec extends HealthEndpointPageUISpec {
}
