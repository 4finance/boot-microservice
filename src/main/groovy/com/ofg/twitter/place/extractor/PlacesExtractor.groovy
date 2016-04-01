package com.ofg.twitter.place.extractor

import com.codahale.metrics.Meter
import com.ofg.twitter.place.model.Tweet
import groovy.transform.PackageScope
import groovy.util.logging.Slf4j
import groovyx.gpars.GParsPool
import org.springframework.cloud.sleuth.Span
import org.springframework.cloud.sleuth.Tracer

import java.util.concurrent.ConcurrentHashMap

@PackageScope
@Slf4j
class PlacesExtractor {

    private final List<PlaceExtractor> placeExtractors
    private final Meter analyzedTweetsMeter
    private final Tracer trace

    PlacesExtractor(List<PlaceExtractor> placeExtractors, Meter analyzedTweetsMeter, Tracer trace) {
        this.placeExtractors = placeExtractors
        this.analyzedTweetsMeter = analyzedTweetsMeter
        this.trace = trace
    }

    Map<String, Optional<Place>> extractPlacesFrom(List<Tweet> tweets) {
        Map<String, Optional<Place>> foundPlaces = new ConcurrentHashMap<>()
        Span parentSpan = trace.createSpan(Thread.currentThread().name)
        GParsPool.withPool {
            tweets.eachParallel { tweet ->
                Span span = trace.createSpan(Thread.currentThread().name, parentSpan)
                foundPlaces << appendExtractedTweet(tweet)
                trace.close(span)
            }
        }
        analyzedTweetsMeter.mark(foundPlaces.size())
        return foundPlaces
    }

    private Map<String, Optional<Place>> appendExtractedTweet(Tweet tweet) {
        String tweetId = tweet.id_str as String
        try {
            return [(tweetId): extractPlace(tweet)]
        } catch (Exception e) {
            log.warn("Unable to extract Tweet", e)
            return [(tweetId): Optional.empty()]
        }
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
