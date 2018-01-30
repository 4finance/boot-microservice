package com.ofg.twitter.health

import com.ofg.twitter.Application
import groovy.json.JsonSlurper
import io.restassured.RestAssured
import io.restassured.response.Response
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@ContextConfiguration(classes = Application)
abstract class HealthEndpointPageSpec extends Specification {

    @LocalServerPort
    int port

    def setupSpec() {
        System.setProperty("APP_ENV", "dev")
    }

    def setup() {
        RestAssured.port = port
    }

    def "Check server status"() {
        when:
            Response response = RestAssured.given().get('/health')
        then:
            response.statusCode == 200
            def json = new JsonSlurper().parseText(response.body.asString())
            json.status == "UP"
            json.diskSpace.status == "UP"
            json.db.status == "UP"
            json.hystrix.status == "UP"
    }

}
