package com.ofg.twitter.geb.microservicejson

import com.ofg.twitter.geb.MicroserviceControllerUISpec
import groovy.json.JsonSlurper
import org.springframework.boot.test.context.SpringBootTest

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

@SpringBootTest(properties = "spring.profiles.active:dev", webEnvironment = DEFINED_PORT)
class MicroserviceJsonMicroserviceControllerUISpec extends MicroserviceControllerUISpec {

    @Override
    protected inputJson() {
        def inputFile = getClass().getResource("/microservice.json")
        return new JsonSlurper().parseText(inputFile.text)
    }
}
