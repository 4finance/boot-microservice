package com.ofg.twitter.place.extractor
import com.codahale.metrics.Meter
import com.ofg.twitter.place.model.Tweet
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

    Map<String, Optional<Place>> extractPlacesFrom(List<Tweet> tweets) {
        Map<String, Optional<Place>> foundPlaces = new ConcurrentHashMap<>()
        GParsPool.withPool {
            tweets.eachParallel { foundPlaces << appendExtractedTweet(it) }
        }
        analyzedTweetsMeter.mark(foundPlaces.size())
        return foundPlaces
    }

    private Map<String, Optional<Place>> appendExtractedTweet(Tweet tweet) {
        return [(tweet.id_str as String): extractPlace(tweet)]
    }

    private Optional<Place> extractPlace(Tweet singleTweet) {
        for (PlaceExtractor placeExtractor : placeExtractors) {
            Optional<Place> extractedPlace = placeExtractor.extractPlaceFrom(singleTweet)
            if (extractedPlace.isPresent()) {
                return extractedPlace
            }
        }
        return Optional.empty()
    }


}
