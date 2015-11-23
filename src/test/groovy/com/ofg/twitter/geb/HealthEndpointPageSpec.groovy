package com.ofg.twitter.geb
import com.ofg.twitter.geb.pages.HealthEndpointPage
import spock.lang.Stepwise

@Stepwise
class HealthEndpointPageSpec extends BaseBootGebSpec {
   
    def "Open health page"() {
        when:
            to HealthEndpointPage
        then:
            at HealthEndpointPage
    }
    
    def "Check server status"(){
        when: 
            parseStatus()
        then:
            json.status == "UP"
            json.diskSpace.status == "UP"
            json.db.status == "UP"
            json.hystrix.status == "UP"        
    }
}
