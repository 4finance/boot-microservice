package com.ofg.twitter.controller.place.extractor

import com.ofg.twitter.controller.place.Place
import groovy.json.JsonSlurper
import groovy.transform.PackageScope
import groovyx.gpars.GParsPool

import java.util.concurrent.ConcurrentHashMap

@PackageScope
class PlacesExtractor {

    private final List<PlaceExtractor> placeExtractors

    PlacesExtractor(List<PlaceExtractor> placeExtractors) {
        this.placeExtractors = placeExtractors
    }

    Map<String, Optional<Place>> extractPlacesFrom(String tweets) {
        Map<String, Optional<Place>> foundPlaces = new ConcurrentHashMap<>()    
        def parsedTweets = new JsonSlurper().parseText(tweets)
        GParsPool.withPool {
            parsedTweets.eachParallel { foundPlaces << appendExtractedTweet(it) }                 
        }
        return foundPlaces
    }

    private Map<String, Optional<Place>> appendExtractedTweet(Object parsedTweets) {
        return [(parsedTweets.id_str as String): extractPlace(parsedTweets)]
    }

    private Optional<Place> extractPlace(Object singleTweet) {
        for (PlaceExtractor placeExtractor : placeExtractors) {
            Optional<Place> extractedPlace = placeExtractor.extractPlaceFrom(singleTweet)
            if (extractedPlace.isPresent()) {
                return extractedPlace
            }
        }
        return Optional.empty()
    }


}
