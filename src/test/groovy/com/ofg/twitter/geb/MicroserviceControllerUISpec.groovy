package com.ofg.twitter.geb

import com.ofg.twitter.geb.pages.SwaggerUIHomePage
import groovy.json.JsonSlurper
import spock.lang.Stepwise

@Stepwise
class MicroserviceControllerUISpec extends BaseBootGebSpec {

    def "Microservice controller is visible on Swagger Page"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            waitFor { metricsMvcEndpointText.displayed }
    }

    def "'Try it put' button is visible for GET operation"() {
        when:
            showMicroservice.click()
        then:
            microserviceJsonText.displayed
        when:
            microserviceJsonText.click()
        then:
            waitFor { microserviceGetTryButton.displayed }
            microserviceGetTryButton.displayed
    }

    def "Try it out and Check respond body and code"() {
        when:
            microserviceGetTryButton.click()
        then:
            waitFor { microserviceGetResponseCode.displayed }
            microserviceGetResponseCode.displayed
            microserviceGetResponseCode.text() == '200'
        and:
            def inputFile = getClass().getResource("/microservice.json")
            def inputJSON = new JsonSlurper().parseText(inputFile.text)
            def outputJSON = new JsonSlurper().parseText(microserviceGetResponseBody.text())
            inputJSON == outputJSON
    }

}
