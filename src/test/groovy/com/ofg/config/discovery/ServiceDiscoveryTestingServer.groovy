package com.ofg.config.discovery

import groovy.transform.PackageScope
import groovy.transform.TypeChecked
import org.apache.curator.test.TestingServer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@TypeChecked
@Configuration
@PackageScope
class ServiceDiscoveryTestingServer  {
    
    @Bean(destroyMethod = 'close')
    TestingServer testingServer(@Value('${service.resolver.url:localhost:2181}') String serviceResolverUrl) {
        return new TestingServer(serviceResolverUrl.split(':').last().toInteger())
    }
}
