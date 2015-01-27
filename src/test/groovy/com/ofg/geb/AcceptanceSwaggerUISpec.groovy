package com.ofg.geb
import geb.spock.GebSpec

class AcceptanceSwaggerUISpec extends GebSpec{
    def "SwaggerUI home page should be visible"(){
        when:
            to SwaggerUIHomePage
        then:
            at SwaggerUIHomePage

    }
}
