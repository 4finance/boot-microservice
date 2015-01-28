package com.ofg.geb
import geb.spock.GebSpec
import spock.lang.Ignore

class AcceptanceSwaggerUISpec extends GebSpec{
    def setupSpec(){
        //TODO
        //run app from console
        def command ="""./gradlew bootRun -Dspring.profiles.active=dev"""
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

    //FIXME api link i page object should be fixed first
    @Ignore
    def "Endpoint API is visible"(){
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage
            assert apiEndpointText.displayed
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
    }
}