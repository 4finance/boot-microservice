package com.ofg.geb
import com.ofg.geb.pages.SwaggerUIHomePage
import com.ofg.twitter.Application
import geb.spock.GebSpec
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration

@ContextConfiguration(loader = SpringApplicationContextLoader.class,classes = Application)
@WebAppConfiguration
@IntegrationTest("spring.profiles.active:dev,stubrunner.skip-local-repo:true")
class AcceptanceSwaggerUISpec extends GebSpec{

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