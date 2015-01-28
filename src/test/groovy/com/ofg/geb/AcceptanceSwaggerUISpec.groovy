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

    @Ignore
    def "Endpoint API is visible"(){
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage


    }

    @Ignore
    def "Endpoint microservice-configuration-controller is visible"(){
        when:
        to SwaggerUIHomePage
        then:
        at SwaggerUIHomePage
    }
    @Ignore
    def "Endpoint health-mvc-endpoint is visible"(){
        //TODO
    }


}
