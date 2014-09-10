package com.ofg.base

import com.ofg.infrastructure.discovery.ServiceDiscoveryConfiguration
import com.ofg.microservice.Application
import groovy.transform.TypeChecked
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@TypeChecked
@Configuration
@Import([ServiceDiscoveryConfiguration, Application])
class ServiceDiscoveryStubbingApplicationConfiguration {}
