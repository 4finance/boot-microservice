package com.ofg.twitter.controller.place.extractor

import com.ofg.twitter.controller.place.Place
import com.ofg.twitter.controller.place.PlacesJsonBuilder
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

@TypeChecked
@Slf4j
class PlacePropagatingWorker implements PropagationWorker {

    public static final String COLLERATOR_DEPENDENCY_NAME = 'collerator'
    
    private final PlacesExtractor placesExtractor
    private final PlacesJsonBuilder placesJsonBuilder
    private final ColleratorClient colleratorClient
    

    @Autowired
    PlacePropagatingWorker(PlacesExtractor placesExtractor, 
                           PlacesJsonBuilder placesJsonBuilder,
                           ColleratorClient colleratorClient) {
        this.placesExtractor = placesExtractor
        this.placesJsonBuilder = placesJsonBuilder
        this.colleratorClient = colleratorClient
    }

    @Override
    void collectAndPropagate(long pairId, String tweets) {
        Map<String, Optional<Place>> extractedPlaces = placesExtractor.extractPlacesFrom(tweets)
        String jsonToPropagate = placesJsonBuilder.buildPlacesJson(pairId, extractedPlaces)
        colleratorClient.populatePlaces(pairId, jsonToPropagate)
        log.debug("Sent json [$jsonToPropagate] to collerator")
    }
}
