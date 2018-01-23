package com.ofg.twitter.geb

import com.ofg.twitter.geb.pages.SwaggerUIHomePage
import spock.lang.Stepwise

@Stepwise
abstract class AcceptanceSwaggerUISpec extends BaseBootGebUISpec {

    def "SwaggerUI home page should be visible"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
    }

    def "Endpoint 'microservice-configuration-controller' is visible"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            waitFor { microserviceController.displayed }
            microserviceControllerOperationsToggle.displayed
        when:
            microserviceControllerOperationsToggle.click()
        then:
            waitFor { microserviceDescriptorGetOperationToggle.displayed }
    }

    def "Endpoint 'health-mvc-endpoint' is visible"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            waitFor { healthMvcEndpoint.displayed }
            healthMvcEndpointOperationsToggle.displayed
    }

    def "Endpoint 'pair-id-controller' is visible"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            waitFor { pairIdController.displayed }
            pairIdControllerOperationsToggle.displayed
        when:
            pairIdControllerOperationsToggle.click()
        then:
            waitFor { pairIdPutOperationToggle.displayed }
    }

}
