package com.ofg.twitter.geb

import com.ofg.twitter.geb.pages.SwaggerUIHomePage
import groovy.json.JsonSlurper
import spock.lang.Stepwise

@Stepwise
abstract class MicroserviceControllerUISpec extends BaseBootGebUISpec {

    def "Microservice controller is visible on Swagger Page"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            waitFor { microserviceController.displayed }
    }

    def "'Try it out' button is visible for GET '/microserviceDescriptor' operation"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            waitFor { microserviceController.displayed }
        when:
            microserviceControllerOperationsToggle.click()
        then:
            waitFor { microserviceDescriptorGetOperationToggle.displayed }
        when:
            microserviceDescriptorGetOperationToggle.click()
        then:
            waitFor { microserviceDescriptorGetOperationTryButton.displayed }
            microserviceDescriptorGetOperationTryButton.displayed
    }

    def "Try it out and Check respond body and code"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            waitFor { microserviceController.displayed }
        when:
            waitFor { microserviceControllerOperationsToggle.displayed }
            microserviceControllerOperationsToggle.click()
            waitFor { microserviceDescriptorGetOperationToggle.displayed }
            microserviceDescriptorGetOperationToggle.click()
            waitFor { microserviceDescriptorGetOperationTryButton.displayed }
            microserviceDescriptorGetOperationTryButton.click()
        then:
            waitFor { microserviceDescriptorGetOperationResponseCode.displayed }
            microserviceDescriptorGetOperationResponseCode.displayed
            microserviceDescriptorGetOperationResponseCode.text() == '200'
        and:
            def inputJSON = inputJson()
            def outputJSON = new JsonSlurper().parseText(microserviceDescriptorGetOperationResponseBody.text())
            inputJSON == outputJSON
    }

    abstract protected def inputJson()

}
