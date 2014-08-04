package com.ofg.base
import com.ofg.microservice.config.Application
import com.ofg.microservice.config.discovery.ServiceDiscoveryTestingServerConfiguration
import groovy.transform.TypeChecked
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@TypeChecked
@Configuration
@Import([ServiceDiscoveryTestingServerConfiguration, Application])
class ServiceDiscoveryStubbingApplicationConfiguration {
}
