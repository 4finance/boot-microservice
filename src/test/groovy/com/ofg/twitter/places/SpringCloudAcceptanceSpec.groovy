package com.ofg.twitter.places

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [ColleratorClientStubConfiguration])
@ActiveProfiles('springCloud')
class SpringCloudAcceptanceSpec extends AbstractAcceptanceSpec {

}
