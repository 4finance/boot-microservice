package com.ofg.config.web

import groovy.transform.TypeChecked
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@TypeChecked
@Configuration
class MockServerConfiguration {
    
    @Bean(initMethod = 'start', destroyMethod = 'shutdownServer')
    HttpMockServer httpMockServer() {
        return new HttpMockServer()
    }
    
}
