package com.ofg.twitter.geb

import com.ofg.twitter.geb.pages.SwaggerUIHomePage
import spock.lang.Stepwise

@Stepwise
class AcceptanceSwaggerUISpec extends BaseBootGebSpec {

    def "SwaggerUI home page should be visible"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
    }


    def "Endpoint microservice-configuration-controller is visible"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            waitFor {metricsMvcEndpointText.displayed}
            metricsMvcEndpointText.displayed
        when:
            showMicroservice.click()
        then:
            microserviceJsonText.displayed
    }

    def "Endpoint health-mvc-endpoint is visible"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            waitFor {healthMvcEndpointText.displayed}
            healthMvcEndpointText.displayed
            showHealthMVCEndpoints.displayed
    }

    def "Endpoint 'pairid' is visible"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
        when:
            waitFor {showPairIdEndpoints.displayed}
            showPairIdEndpoints.click()
        then:
            waitFor { pairIdPutText.displayed }
            pairIdPutText.text() == "/api/{pairId}"

    }

}