package com.ofg.twitter.geb.springcloud

import com.ofg.twitter.geb.HealthEndpointPageUISpec
import org.springframework.boot.test.IntegrationTest

@IntegrationTest("spring.profiles.active:dev")
class SpringCloudHealthEndpointUISpec extends HealthEndpointPageUISpec {
}
