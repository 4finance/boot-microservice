package com.ofg.twitter.controller.place.extractor
import com.ofg.twitter.controller.place.Place
import com.ofg.twitter.controller.place.extractor.metrics.MatchProbabilityMetrics
import com.ofg.twitter.tweets.Tweets
import groovy.json.JsonSlurper
import spock.lang.Specification

class CoordinatesPlaceExtractorSpec extends Specification {

    MatchProbabilityMetrics metrics = Stub()
    CityFinder cityFinder = Stub()
    CoordinatesPlaceExtractor coordinatesPlaceExtractor = new CoordinatesPlaceExtractor(cityFinder, metrics)

    def 'should return high probability of result'() {
        expect:
            coordinatesPlaceExtractor.placeResolutionProbability == PlaceExtractor.PlaceResolutionProbability.HIGH
    }

    def 'should return non null name of origin of place resolution'() {
        expect:
            coordinatesPlaceExtractor.origin
    }

    def 'should return extracted place by cross referencing coordinates with city'() {
        given:
            String tweet = Tweets.TWEET_WITH_COORDINATES
            CoordinatesPlaceExtractor coordinatesPlaceExtractor = new CoordinatesPlaceExtractor(NEW_PLACE_RETURNING_CITY_FINDER, metrics)
        when:
            Optional<Place> extractedPlace = coordinatesPlaceExtractor.extractPlaceFrom(new JsonSlurper().parseText(tweet))
        then:
            extractedPlace.present
    }

    def 'should return empty place is place section is missing'() {
        given:
            String tweet = Tweets.TWEET_WITHOUT_COORDINATES
            CoordinatesPlaceExtractor coordinatesPlaceExtractor = new CoordinatesPlaceExtractor(MISSING_PLACE_RETURNING_CITY_FINDER, metrics)
        when:
            Optional<Place> extractedPlace = coordinatesPlaceExtractor.extractPlaceFrom(new JsonSlurper().parseText(tweet))
        then:
            !extractedPlace.present
    }

    private static final CityFinder NEW_PLACE_RETURNING_CITY_FINDER = new CityFinder(null , 'someUrl') {
        @Override
        Optional<Place.PlaceDetails> findCityFromCoordinates(long latitude, long longitude) {
            return Optional.of(new Place.PlaceDetails('Washington', 'US'))
        }
    }

    private static final CityFinder MISSING_PLACE_RETURNING_CITY_FINDER = new CityFinder(null, 'someUrl') {
        @Override
        Optional<Place.PlaceDetails> findCityFromCoordinates(long latitude, long longitude) {
            return Optional.empty()
        }
    }

}
