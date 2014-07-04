package com.ofg.microservice.infrastructure.discovery
import com.ofg.infrastructure.discovery.ServiceResolverConfiguration
import groovy.transform.TypeChecked
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@TypeChecked
@Configuration
@Import(ServiceResolverConfiguration)
class ServiceDiscoveryConfiguration {
}
