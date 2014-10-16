package com.ofg.twitter.places

import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import com.ofg.twitter.controller.place.extractor.ColleratorClient
import groovy.transform.CompileStatic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@CompileStatic
@Configuration
class ColleratorClientStubConfiguration {

    @Bean @Primary
    ColleratorClient colleratorClientStub(ServiceRestClient serviceRestClient) {
        return new ColleratorClientStub(serviceRestClient)
    }
}
