package com.ofg.twitter.controller.place.extractor

import com.ofg.twitter.controller.place.Place
import com.ofg.twitter.controller.place.extractor.PlaceExtractor.PlaceResolutionProbability
import com.ofg.twitter.controller.place.extractor.metrics.MatchProbabilityMetrics
import groovy.json.JsonSlurper
import spock.lang.Specification

import static com.ofg.twitter.tweets.Tweets.TWEET_WITH_PLACE
import static com.ofg.twitter.tweets.Tweets.TWEET_WITHOUT_A_PLACE

class PlaceSectionExtractorSpec extends Specification {

    MatchProbabilityMetrics matchProbabilityMetrics = Mock()
    PlaceSectionExtractor placeSectionExtractor = new PlaceSectionExtractor(matchProbabilityMetrics)

    def 'should return high probability of result'() {
        expect:
            placeSectionExtractor.placeResolutionProbability == PlaceResolutionProbability.HIGH
    }

    def 'should return non null name of origin of place resolution'() {
        expect:
            placeSectionExtractor.origin
    }

    def 'should return extracted place from tweet'() {
        given:
            def tweetWithPlace = parseTweet(TWEET_WITH_PLACE)
        when:
            Optional<Place> extractedPlace = placeSectionExtractor.extractPlaceFrom(tweetWithPlace)
        then:
            extractedPlace.present
            extractedPlace.get().placeDetails.countryCode == 'US'
            extractedPlace.get().placeDetails.name == 'Washington'
    }

    def 'should return empty place is place section is missing'() {
        given:
            def tweetWithoutPlace = parseTweet(TWEET_WITHOUT_A_PLACE)
        when:
            Optional<Place> extractedPlace = placeSectionExtractor.extractPlaceFrom(tweetWithoutPlace)
        then:
            !extractedPlace.present
    }

    def 'should update match probability metrics when tweet contains place section'() {
        given:
            def tweetWithPlace = parseTweet(TWEET_WITH_PLACE)
        when:
            placeSectionExtractor.extractPlaceFrom(tweetWithPlace)
        then:
            1 * matchProbabilityMetrics.update(placeSectionExtractor.placeResolutionProbability)
    }

    def 'should not update match probability metrics when place section is missing'() {
        given:
            def tweetWithoutPlace = parseTweet(TWEET_WITHOUT_A_PLACE)
        when:
            placeSectionExtractor.extractPlaceFrom(tweetWithoutPlace)
        then:
            0 * matchProbabilityMetrics.update(_)
    }

    private def parseTweet(String tweet) {
        return new JsonSlurper().parseText(tweet)
    }
}
