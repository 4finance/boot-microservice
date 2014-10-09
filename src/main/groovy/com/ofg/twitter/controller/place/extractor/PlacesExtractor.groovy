package com.ofg.twitter.controller.place.extractor

import com.codahale.metrics.Meter
import com.ofg.twitter.controller.place.Place
import groovy.json.JsonSlurper
import groovy.transform.PackageScope
import groovyx.gpars.GParsPool

import java.util.concurrent.ConcurrentHashMap

@PackageScope
class PlacesExtractor {

    private final List<PlaceExtractor> placeExtractors
    private final Meter analyzedTweetsMeter

    PlacesExtractor(List<PlaceExtractor> placeExtractors, Meter analyzedTweetsMeter) {
        this.placeExtractors = placeExtractors
        this.analyzedTweetsMeter = analyzedTweetsMeter
    }

    Map<String, Optional<Place>> extractPlacesFrom(String tweets) {
        Map<String, Optional<Place>> foundPlaces = new ConcurrentHashMap<>()    
        def parsedTweets = new JsonSlurper().parseText(tweets)
        GParsPool.withPool {
            parsedTweets.eachParallel { foundPlaces << appendExtractedTweet(it) }                 
        }
        analyzedTweetsMeter.mark(foundPlaces.size())
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
