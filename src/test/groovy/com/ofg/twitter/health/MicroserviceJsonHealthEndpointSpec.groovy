package com.ofg.twitter.health

import org.springframework.boot.test.context.SpringBootTest

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(properties = ["spring.profiles.active:dev", "endpoints.health.sensitive:false", "management.security.enabled:false"], webEnvironment = RANDOM_PORT)
class MicroserviceJsonHealthEndpointSpec extends HealthEndpointPageSpec {
}
