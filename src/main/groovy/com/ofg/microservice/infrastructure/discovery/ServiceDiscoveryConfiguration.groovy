package com.ofg.microservice.infrastructure.discovery
import com.ofg.infrastructure.discovery.ServiceResolverConfiguration
import com.ofg.microservice.config.Profiles
import groovy.transform.TypeChecked
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile

@TypeChecked
@Configuration
@Import(ServiceResolverConfiguration)
@Profile([Profiles.PRODUCTION, Profiles.TEST])
class ServiceDiscoveryConfiguration {
}
