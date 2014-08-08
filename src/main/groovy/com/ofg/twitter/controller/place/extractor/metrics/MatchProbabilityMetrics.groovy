package com.ofg.twitter.controller.place.extractor.metrics

import com.codahale.metrics.Meter
import com.codahale.metrics.MetricRegistry
import com.ofg.twitter.controller.place.extractor.PlaceExtractor.PlaceResolutionProbability

class MatchProbabilityMetrics {

    private final Map<PlaceResolutionProbability, Meter> probabilityMeters = [:]

    MatchProbabilityMetrics(MetricRegistry metricRegistry) {
        registerProbabilityMetrics(metricRegistry)
    }

    void update(PlaceResolutionProbability probability) {
        probabilityMeters[probability].mark()
    }

    private void registerProbabilityMetrics(MetricRegistry metricRegistry) {
        PlaceResolutionProbability.values().each { probability ->
            probabilityMeters[probability] = metricRegistry.meter("twitter.places.analyzed.probability.$probability")
        }
    }
}
