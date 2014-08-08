package com.ofg.twitter.controller.place.extractor.metrics

import com.codahale.metrics.MetricRegistry
import com.ofg.infrastructure.metrics.registry.MetricsRegistryConfiguration
import groovy.transform.TypeChecked
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@TypeChecked
@Configuration
@Import(MetricsRegistryConfiguration)
class ExtractorMetricsConfiguration {

    @Bean
    MatchProbabilityMetrics matchProbabilityMetrics(MetricRegistry metricRegistry) {
        return new MatchProbabilityMetrics(metricRegistry)
    }
}
