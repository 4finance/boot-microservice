package com.ofg.twitter.geb
import com.ofg.twitter.geb.pages.HealthEndpointPage
import groovy.json.JsonSlurper
import spock.lang.Stepwise

@Stepwise
abstract class HealthEndpointPageUISpec extends BaseBootGebUISpec {
   
    def "Open health page"() {
        when:
            to HealthEndpointPage
        then:
            at HealthEndpointPage
    }
    
    def "Check server status"(){
        given:
            String text = statusJson.text()
        when:
            def json = new JsonSlurper().parseText(text)
        then:
            json.status == "UP"
            json.diskSpace.status == "UP"
            json.db.status == "UP"
            json.hystrix.status == "UP"        
    }

}
