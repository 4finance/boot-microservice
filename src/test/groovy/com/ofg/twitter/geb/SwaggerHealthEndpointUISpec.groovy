package com.ofg.twitter.geb

import com.ofg.twitter.geb.pages.SwaggerUIHomePage
import spock.lang.Stepwise
import spock.lang.Unroll

@Stepwise
@Unroll
abstract class SwaggerHealthEndpointUISpec extends BaseBootGebUISpec {

    def "Check 'health-mvc-endpoint' visibility of '#operationToggleText' operation"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
        when:
            waitFor { healthMvcEndpointOperationsToggle.displayed }
            healthMvcEndpointOperationsToggle.click()
        then:
            waitFor { healthMvcEndpointOperationsContainer.displayed }
            healthMvcEndpointOperationToggleTemplate(operationToggleText).displayed
        where:
            operationToggleText << ['/health', '/health.json']
    }

}
