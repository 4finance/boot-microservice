package com.ofg.twitter.controller.place.extractor

import com.ofg.infrastructure.discovery.ServiceResolver
import com.ofg.infrastructure.web.resttemplate.RestTemplate
import com.ofg.twitter.controller.place.PlacesJsonBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PlaceExtractorConfiguration {

    @Bean
    CityFinder cityFinder(RestTemplate restTemplate, @Value('${city.finding.service.url:http://api.openweathermap.org/data/2.5/weather}') String cityFindingServiceUrl) {
        return new CityFinder(restTemplate, cityFindingServiceUrl)
    }
    
    @Bean
    PlacesExtractor placesExtractor(CityFinder cityFinder) {
        return new PlacesExtractor([new PlaceSectionExtractor(), new CoordinatesSectionExtractor(cityFinder)])
    }
    
    @Bean
    PlacesJsonBuilder placesJsonBuilder() {
        return new PlacesJsonBuilder()
    }
    
    @Bean
    PropagationWorker propagationWorker(PlacesExtractor placesExtractor,
                                        PlacesJsonBuilder placesJsonBuilder,
                                        ServiceResolver serviceResolver,
                                        RestTemplate restTemplate) {
        return new PlacePropagatingWorker(placesExtractor, placesJsonBuilder, serviceResolver, restTemplate)
    }

}
