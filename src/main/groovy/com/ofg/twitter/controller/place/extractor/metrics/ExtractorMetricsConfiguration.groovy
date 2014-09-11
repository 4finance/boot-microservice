package com.ofg.twitter.controller.place.extractor.metrics
import com.codahale.metrics.MetricRegistry
import groovy.transform.TypeChecked
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@TypeChecked
@Configuration
class ExtractorMetricsConfiguration {

    @Bean
    MatchProbabilityMetrics matchProbabilityMetrics(MetricRegistry metricRegistry) {
        return new MatchProbabilityMetrics(metricRegistry)
    }
}
