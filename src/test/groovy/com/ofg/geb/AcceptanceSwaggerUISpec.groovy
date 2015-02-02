package com.ofg.geb

import com.ofg.geb.pages.SwaggerUIHomePage
import geb.spock.GebSpec

class AcceptanceSwaggerUISpec extends GebSpec{
    def setupSpec(){
        //TODO
        //run app from console
        def command ="""./gradlew bootRun -Dspring.profiles.active=dev -Dstubrunner.skip-local-repo=true"""
        command.execute()
        sleep 30000
    }

    def cleanupSpec(){
        //TODO
        //kill app from setup
    }

    def "SwaggerUI home page should be visible"(){
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
    }


    def "Endpoint microservice-configuration-controller is visible"(){
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            assert metricsMvcEndpointText.displayed
            showMicroservice.click()
            assert microserviceJsonText.displayed
    }

    def "Endpoint health-mvc-endpoint is visible"(){
        when:
           to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            assert healthMvcEndpointText.displayed
            assert showHealthMVCEndpoints.displayed
    }





}