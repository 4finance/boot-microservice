package com.ofg.twitter.geb
import com.ofg.twitter.geb.pages.SwaggerUIHomePage
import spock.lang.Stepwise
import spock.lang.Unroll

@Stepwise
@Unroll
abstract class SwaggerHealthEndpointUISpec extends BaseBootGebUISpec {

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
    }

    def "Check visibility of operations and their paths for 'health-mvc-endpoint' #httpOperation"() {
        expect:
            waitFor { $("#health-mvc-endpoint_endpoint_list li.$httpOperation span.path a")*.displayed }
            getTextfromHealthOperation(httpOperation).contains path

        where:
            httpOperation || path
            "get"         || "/health"
            "delete"      || "/health"
            "head"        || "/health"
            "options"     || "/health"
            "post"        || "/health"
            "patch"       || "/health"
            "put"         || "/health"
    }

    String getTextfromHealthOperation(String http_operation) {
        return $("#health-mvc-endpoint_endpoint_list li.$http_operation span.path a")*.text()
    }

}
