package com.ofg.twitter.controller.place.extractor

import com.codahale.metrics.Meter
import com.codahale.metrics.MetricRegistry
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import com.ofg.twitter.controller.place.PlacesJsonBuilder
import com.ofg.twitter.controller.place.extractor.metrics.ExtractorMetricsConfiguration
import com.ofg.twitter.controller.place.extractor.metrics.MatchProbabilityMetrics
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(ExtractorMetricsConfiguration)
class PlaceExtractorConfiguration {

    @Bean
    CityFinder cityFinder(ServiceRestClient serviceRestClient, @Value('${city.finding.service.url:http://api.openweathermap.org/data/2.5/weather}') String cityFindingServiceUrl) {
        return new CityFinder(new WeatherClient(serviceRestClient, cityFindingServiceUrl))
    }
    
    @Bean
    PlacesExtractor placesExtractor(CityFinder cityFinder, MatchProbabilityMetrics matchProbabilityMetrics, MetricRegistry metricRegistry) {
        Meter analyzedTweetsMeter = metricRegistry.meter('twitter.places.analyzed.tweets')
        List<PlaceExtractor> placeExtractors = [ new PlaceSectionExtractor(matchProbabilityMetrics),
                                                 new CoordinatesSectionExtractor(cityFinder, matchProbabilityMetrics) ]
        return new PlacesExtractor(placeExtractors, analyzedTweetsMeter)
    }
    
    @Bean
    PlacesJsonBuilder placesJsonBuilder() {
        return new PlacesJsonBuilder()
    }
    
    @Bean
    PropagationWorker propagationWorker(PlacesExtractor placesExtractor,
                                        PlacesJsonBuilder placesJsonBuilder,
                                        ColleratorClient colleratorClient) {
        return new PlacePropagatingWorker(placesExtractor, placesJsonBuilder, colleratorClient)
    }

    @Bean
    ColleratorClient colleratorClient(ServiceRestClient serviceRestClient) {
        return new ColleratorClient(serviceRestClient)
    }

}
