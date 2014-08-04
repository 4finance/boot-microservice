package com.ofg.twitter.controller.place.extractor

import com.ofg.twitter.controller.place.Place
import com.ofg.twitter.tweets.Tweets
import groovy.json.JsonSlurper
import spock.lang.Specification

class PlaceSectionExtractorSpec extends Specification {

    def 'should return high probability of result'() {
        expect:
            new PlaceSectionExtractor().placeResolutionProbability == PlaceExtractor.PlaceResolutionProbability.HIGH
    }

    def 'should return non null name of origin of place resolution'() {
        expect:
            new PlaceSectionExtractor().origin
    }

    def 'should return extracted place from tweet'() {
        given:
            String tweet = Tweets.TWEET_WITH_PLACE
            PlaceSectionExtractor placeSectionExtractor = new PlaceSectionExtractor()
        when:
            Optional<Place> extractedPlace = placeSectionExtractor.extractPlaceFrom(new JsonSlurper().parseText(tweet))
        then:
            extractedPlace.present
            extractedPlace.get().placeDetails.countryCode == 'US'
            extractedPlace.get().placeDetails.name == 'Washington'
    }

    def 'should return empty place is place section is missing'() {
        given:
            String tweet = Tweets.TWEET_WITHOUT_A_PLACE
            PlaceSectionExtractor placeSectionExtractor = new PlaceSectionExtractor()
        when:
            Optional<Place> extractedPlace = placeSectionExtractor.extractPlaceFrom(new JsonSlurper().parseText(tweet))
        then:
            !extractedPlace.present
    }

}
