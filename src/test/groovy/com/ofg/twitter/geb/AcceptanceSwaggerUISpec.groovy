package com.ofg.twitter.geb

import com.ofg.twitter.geb.pages.SwaggerUIHomePage
import com.ofg.twitter.Application
import geb.spock.GebSpec
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Stepwise

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = Application)
@WebAppConfiguration
@IntegrationTest("spring.profiles.active:dev,stubrunner.skip-local-repo:true")
@Stepwise
class AcceptanceSwaggerUISpec extends GebSpec {

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