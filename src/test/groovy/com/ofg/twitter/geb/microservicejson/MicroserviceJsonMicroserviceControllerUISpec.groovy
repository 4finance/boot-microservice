package com.ofg.twitter.geb.microservicejson

import com.ofg.twitter.geb.MicroserviceControllerUISpec
import groovy.json.JsonSlurper
import org.springframework.boot.test.IntegrationTest
import spock.lang.Ignore

@IntegrationTest("spring.profiles.active:dev")
@Ignore("Still there are some issues with ids of page components")
class MicroserviceJsonMicroserviceControllerUISpec extends MicroserviceControllerUISpec {

    @Override
    protected inputJson() {
        def inputFile = getClass().getResource("/microservice.json")
        return new JsonSlurper().parseText(inputFile.text)
    }
}
