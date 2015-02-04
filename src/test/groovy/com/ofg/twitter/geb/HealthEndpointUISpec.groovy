package com.ofg.twitter.geb

import com.ofg.twitter.geb.pages.SwaggerUIHomePage
import com.ofg.twitter.Application
import geb.spock.GebSpec
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Stepwise
import spock.lang.Unroll

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = Application)
@WebAppConfiguration
@IntegrationTest("spring.profiles.active:dev,stubrunner.skip-local-repo:true")
@Stepwise
@Unroll
class HealthEndpointUISpec extends GebSpec {

    def "Setup step"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            showHealthMVCEndpoints.click()
            waitFor { healthEndpointsTable.displayed }
            waitFor { healthEndpointTraceText.displayed }
    }

    def "Check visibility of operations and their paths for 'health-mvc-endpoint' #httpOperation"() {

        expect:
            getTextfromHealthOperation(httpOperation) == path

        where:
            httpOperation || path
            "get"         || "/health"
            "delete"      || "/health"
            "head"        || "/health"
            "options"     || "/health"
            "post"        || "/health"
            "patch"       || "/health"
            "put"         || "/health"
            "trace"       || "/health"

    }

    String getTextfromHealthOperation(String http_operation) {
        waitFor { $("#resource_health-mvc-endpoint li." + http_operation + " span.path a") }
        return $("#resource_health-mvc-endpoint li." + http_operation + " span.path a").text()
    }

}
