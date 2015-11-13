package com.ofg.twitter.places

import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [ColleratorClientStubConfiguration])
@ActiveProfiles('springCloud')
class SpringCloudAcceptanceSpec extends AbstractAcceptanceSpec {

}
