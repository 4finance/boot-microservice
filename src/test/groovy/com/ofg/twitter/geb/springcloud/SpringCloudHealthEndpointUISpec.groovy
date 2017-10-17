package com.ofg.twitter.geb.springcloud

import com.ofg.twitter.geb.HealthEndpointPageUISpec
import org.springframework.boot.test.context.SpringBootTest

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

@SpringBootTest(properties = "spring.profiles.active:dev,springCloud", webEnvironment = DEFINED_PORT)
class SpringCloudHealthEndpointUISpec extends HealthEndpointPageUISpec {
}
