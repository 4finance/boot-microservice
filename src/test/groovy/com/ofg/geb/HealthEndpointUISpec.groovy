package com.ofg.geb

import geb.spock.GebSpec
import spock.lang.Unroll


@Unroll
class HealthEndpointUISpec extends GebSpec {

    def setupSpec(){
        to SwaggerUIHomePage
        showHealthMVCEndpoints.click()
    }

    def "Check visibility of operations and their paths for 'health-mvc-endpoint' #httpOperation"(){

        expect:
        getTextfromHealthOperation(httpOperation)==path

        where:
        httpOperation  |   path
        "get"           |   "/health"
        "delete"        |   "/health"
        "head"          |   "/health"
        "options"       |   "/health"
        "post"          |   "/health"
        "patch"         |   "/health"
        "put"           |   "/health"
        "trace"         |   "/health"


    }

     String getTextfromHealthOperation(String http_operation){
         waitFor { $("#resource_health-mvc-endpoint li."+http_operation+" span.path a")}
         return  $("#resource_health-mvc-endpoint li."+http_operation+" span.path a").text()
    }

}
