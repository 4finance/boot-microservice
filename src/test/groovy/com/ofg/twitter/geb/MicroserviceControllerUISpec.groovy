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
            waitFor { metricsMvcEndpointText.displayed }
    }

    def "'Try it out' button is visible for GET operation"() {
        when:
            to SwaggerUIHomePage
            showMicroservice.click()
        then:
            microserviceDescriptorText.displayed
        when:
            microserviceDescriptorText.click()
        then:
            waitFor { microserviceGetTryButton.displayed }
            microserviceGetTryButton.displayed
    }

    def "Try it out and Check respond body and code"() {
        when:
            to SwaggerUIHomePage
            waitFor { showMicroservice.displayed }
            showMicroservice.click()
            waitFor { microserviceDescriptorText.displayed }
            microserviceDescriptorText.click()
            waitFor { microserviceGetTryButton.displayed }
            microserviceGetTryButton.click()
        then:
            waitFor { microserviceGetResponseCode.displayed }
            microserviceGetResponseCode.displayed
            microserviceGetResponseCode.text() == '200'
        and:
            def inputJSON = inputJson()
            def outputJSON = new JsonSlurper().parseText(microserviceGetResponseBody.text())
            inputJSON == outputJSON
    }

    abstract protected def inputJson()

}
