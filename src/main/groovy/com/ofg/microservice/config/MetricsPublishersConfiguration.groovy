package com.ofg.microservice.config

import com.codahale.metrics.MetricRegistry
import com.ofg.infrastructure.metrics.publishing.JmxPublisher
import com.ofg.infrastructure.metrics.registry.MetricsRegistryConfiguration
import groovy.transform.TypeChecked
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

import static java.util.concurrent.TimeUnit.MINUTES
import static java.util.concurrent.TimeUnit.MILLISECONDS

@TypeChecked
@Configuration
@Import(MetricsRegistryConfiguration)
class MetricsPublishersConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    JmxPublisher jmxPublisher(MetricRegistry metricRegistry) {
        return new JmxPublisher(metricRegistry, MINUTES, MILLISECONDS)
    }
}
