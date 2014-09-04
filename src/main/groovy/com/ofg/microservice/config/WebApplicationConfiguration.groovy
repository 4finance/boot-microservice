package com.ofg.microservice.config

import com.ofg.infrastructure.healthcheck.HealthCheckConfiguration
import com.ofg.infrastructure.metrics.registry.MetricsRegistryConfiguration
import com.ofg.infrastructure.web.config.SwaggerConfiguration
import com.ofg.infrastructure.web.config.WebInfrastructureConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import([WebInfrastructureConfiguration, MetricsRegistryConfiguration, HealthCheckConfiguration, SwaggerConfiguration])
class WebApplicationConfiguration {
}
