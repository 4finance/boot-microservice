package com.ofg.microservice.infrastructure.correlationid

import groovy.transform.TypeChecked
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@TypeChecked
@Configuration
class CorrelationIdConfiguration {

    @Bean
    CorrelationIdAspect correlationIdAspect() {
        return new CorrelationIdAspect()
    }

}
