package com.ofg.twitter.health

import org.springframework.boot.test.context.SpringBootTest

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

@SpringBootTest(properties = ["spring.profiles.active:dev,springCloud", "endpoints.health.sensitive:false", "management.security.enabled:false"], webEnvironment = DEFINED_PORT)
class SpringCloudHealthEndpointSpec extends HealthEndpointPageSpec {
}
