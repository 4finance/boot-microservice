package com.ofg.microservice.config

import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.graphite.Graphite
import com.ofg.infrastructure.metrics.publishing.GraphitePublisher
import com.ofg.infrastructure.metrics.publishing.GraphitePublisher.PublishingInterval
import com.ofg.infrastructure.metrics.publishing.JmxPublisher
import com.ofg.infrastructure.metrics.registry.MetricsRegistryConfiguration
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

import static java.util.concurrent.TimeUnit.*

@TypeChecked
@Configuration
@Import(MetricsRegistryConfiguration)
class MetricsPublishersConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    JmxPublisher jmxPublisher(MetricRegistry metricRegistry) {
        return new JmxPublisher(metricRegistry, MINUTES, MILLISECONDS)
    }

    @Bean
    Graphite graphite(@Value('${graphite.host}') String hostname, @Value('${graphite.port}') int port) {
        return new Graphite(new InetSocketAddress(hostname, port))
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    GraphitePublisher graphitePublisher(Graphite graphite, MetricRegistry metricRegistry) {
        PublishingInterval publishingInterval = new PublishingInterval(15, SECONDS)
        return new GraphitePublisher(graphite, publishingInterval, metricRegistry, MINUTES, MILLISECONDS)
    }
}
