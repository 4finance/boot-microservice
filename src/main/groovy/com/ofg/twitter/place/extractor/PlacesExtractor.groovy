package com.ofg.twitter.place.extractor

import com.codahale.metrics.Meter
import com.ofg.twitter.place.model.Tweet
import groovy.transform.PackageScope
import groovy.util.logging.Slf4j
import groovyx.gpars.GParsPool
import org.springframework.cloud.sleuth.Span
import org.springframework.cloud.sleuth.Trace
import org.springframework.cloud.sleuth.TraceContextHolder
import org.springframework.cloud.sleuth.TraceScope

import java.util.concurrent.ConcurrentHashMap

@PackageScope
@Slf4j
class PlacesExtractor {

    private final List<PlaceExtractor> placeExtractors
    private final Meter analyzedTweetsMeter
    private final Trace trace

    PlacesExtractor(List<PlaceExtractor> placeExtractors, Meter analyzedTweetsMeter, Trace trace) {
        this.placeExtractors = placeExtractors
        this.analyzedTweetsMeter = analyzedTweetsMeter
        this.trace = trace
    }

    Map<String, Optional<Place>> extractPlacesFrom(List<Tweet> tweets) {
        Map<String, Optional<Place>> foundPlaces = new ConcurrentHashMap<>()
        Span span = TraceContextHolder.currentSpan
        GParsPool.withPool {
            tweets.eachParallel { tweet ->
                TraceScope traceScope = trace.startSpan(Thread.currentThread().name, span)
                foundPlaces << appendExtractedTweet(tweet)
                traceScope.close()
                TraceContextHolder.removeCurrentSpan()
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
