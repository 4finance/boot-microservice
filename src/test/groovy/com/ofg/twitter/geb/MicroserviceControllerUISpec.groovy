package com.ofg.twitter.geb

import com.ofg.twitter.Application
import com.ofg.twitter.geb.pages.SwaggerUIHomePage
import geb.spock.GebSpec
import groovy.json.JsonSlurper
import org.apache.log4j.helpers.Loader
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Stepwise

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = Application)
@WebAppConfiguration
@IntegrationTest("spring.profiles.active:dev,stubrunner.skip-local-repo:true")
@Stepwise
class MicroserviceControllerUISpec extends GebSpec {

    def "Microservice controller is visible on Swagger Page"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            metricsMvcEndpointText.displayed
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
            def inputFile = Loader.getResource("microservice.json")
            def inputJSON = new JsonSlurper().parseText(inputFile.text)
            def outputJSON = new JsonSlurper().parseText(microserviceGetResponseBody.text())
            inputJSON == outputJSON
    }

}
