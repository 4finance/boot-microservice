package com.ofg.twitter.geb.microservicejson

import com.ofg.twitter.geb.SwaggerHealthEndpointUISpec
import org.springframework.boot.test.IntegrationTest

@IntegrationTest("spring.profiles.active:dev")
class MicroserviceJsonSwaggerHealthEndpointUISpec extends SwaggerHealthEndpointUISpec {
}
