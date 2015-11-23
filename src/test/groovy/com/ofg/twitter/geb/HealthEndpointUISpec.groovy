package com.ofg.twitter.geb
import com.ofg.twitter.geb.pages.SwaggerUIHomePage
import spock.lang.Stepwise
import spock.lang.Unroll

@Stepwise
@Unroll
abstract class HealthEndpointUISpec extends BaseBootGebSpec {

    def "Setup step"() {
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
        when:
            waitFor { showHealthMVCEndpoints.displayed }
            showHealthMVCEndpoints.click()
        then:
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
        waitFor { $("#resource_health-mvc-endpoint li.$http_operation span.path a").displayed }
        return $("#resource_health-mvc-endpoint li.$http_operation span.path a").text()
    }

}
