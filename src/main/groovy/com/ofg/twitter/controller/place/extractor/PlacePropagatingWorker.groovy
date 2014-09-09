package com.ofg.twitter.controller.place.extractor

import com.ofg.infrastructure.discovery.ServiceResolver
import com.ofg.twitter.controller.place.Place
import com.ofg.twitter.controller.place.PlacesJsonBuilder
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.client.RestOperations

@TypeChecked
@Slf4j
class PlacePropagatingWorker implements PropagationWorker {

    public static final String COLLERATOR_DEPENDENCY_NAME = 'collerator'
    
    private final PlacesExtractor placesExtractor
    private final PlacesJsonBuilder placesJsonBuilder
    private final ServiceResolver serviceResolver
    private final RestOperations restTemplate
    

    @Autowired
    PlacePropagatingWorker(PlacesExtractor placesExtractor, 
                           PlacesJsonBuilder placesJsonBuilder,
                           ServiceResolver serviceResolver,
                           RestOperations restTemplate) {
        this.placesExtractor = placesExtractor
        this.placesJsonBuilder = placesJsonBuilder
        this.serviceResolver = serviceResolver
        this.restTemplate = restTemplate
    }

    @Override
    void collectAndPropagate(long pairId, String tweets) {
        Map<String, Optional<Place>> extractedPlaces = placesExtractor.extractPlacesFrom(tweets)
        String jsonToPropagate = placesJsonBuilder.buildPlacesJson(pairId, extractedPlaces)
        restTemplate.postForObject("${serviceResolver.getUrl(COLLERATOR_DEPENDENCY_NAME).get()}/$pairId", jsonToPropagate, String)
        log.debug("Sent json [$jsonToPropagate] to collerator")
    }
}
