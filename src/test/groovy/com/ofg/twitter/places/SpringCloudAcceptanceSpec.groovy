package com.ofg.twitter.places

import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [ColleratorClientStubConfiguration, LoadBalancedRestTemplateConfiguration])
@ActiveProfiles('springCloud')
class SpringCloudAcceptanceSpec extends AbstractAcceptanceSpec {

}
