package com.ofg.geb

import com.ofg.geb.pages.SwaggerUIHomePage
import com.ofg.twitter.Application
import geb.spock.GebSpec
import groovy.json.JsonSlurper
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
        microserviceJsonText.click()
        waitFor { microserviceGetTryButton.displayed }
        microserviceGetTryButton.displayed


    }

    def "Try it out and Check respond body and code"() {
        when:
        microserviceGetTryButton.click()
        waitFor { microserviceGetResponseCode.displayed }
        then:
        assert microserviceGetResponseCode.displayed
        assert microserviceGetResponseCode.text() == '200'
        def inputFile = new File("src/main/resources/microservice.json")
        def inputJSON = new JsonSlurper().parseText(inputFile.text)
        def outputJSON = new JsonSlurper().parseText(microserviceGetResponseBody.text())
        inputJSON == outputJSON


    }

}
