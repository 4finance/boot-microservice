package com.ofg.twitter.geb
import org.springframework.boot.test.IntegrationTest

@IntegrationTest("spring.profiles.active:dev,springCloud")
class SpringCloudHealthEndpointUISpec extends SwaggerHealthEndpointUISpec {

}
