package com.ofg.twitter.geb.springcloud

import com.ofg.twitter.geb.AcceptanceSwaggerUISpec
import org.springframework.boot.test.IntegrationTest

@IntegrationTest("spring.profiles.active:dev,springCloud")
class SpringCloudAcceptanceSwaggerUISpec extends AcceptanceSwaggerUISpec {
}
