package com.ofg.twitter.geb.springcloud

import com.ofg.twitter.geb.SwaggerHealthEndpointUISpec
import org.springframework.boot.test.IntegrationTest

@IntegrationTest("spring.profiles.active:dev")
class SpringCloudSwaggerHealthEndpointUISpec extends SwaggerHealthEndpointUISpec {

}
