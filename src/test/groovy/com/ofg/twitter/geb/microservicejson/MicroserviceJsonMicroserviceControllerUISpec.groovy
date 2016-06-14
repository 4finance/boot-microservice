package com.ofg.twitter.geb.microservicejson

import com.ofg.twitter.geb.MicroserviceControllerUISpec
import groovy.json.JsonSlurper
import org.springframework.boot.test.IntegrationTest

@IntegrationTest("spring.profiles.active:dev")
class MicroserviceJsonMicroserviceControllerUISpec extends MicroserviceControllerUISpec {

    @Override
    protected inputJson() {
        def inputFile = getClass().getResource("/microservice.json")
        return new JsonSlurper().parseText(inputFile.text)
    }
}
