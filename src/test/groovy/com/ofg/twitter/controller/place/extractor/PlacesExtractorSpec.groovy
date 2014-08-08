package com.ofg.twitter.controller.place.extractor

import com.codahale.metrics.Meter
import com.jayway.awaitility.Awaitility
import com.ofg.twitter.controller.place.Place
import spock.lang.Specification

import static com.jayway.awaitility.Duration.ONE_SECOND

class PlacesExtractorSpec extends Specification {

    def 'should extract places from two'() {
        given:
            List<PlaceExtractor>  placeExtractors = [new ByTweetIdPlacesExtractor()]
            PlacesExtractor placesExtractor = new PlacesExtractor(placeExtractors, Stub(Meter))
            String tweets = '[{"id_str" : "1" }, {"id_str" : "2" }, {"id_str" : "3" }]'
        when:
            Map<String, Optional<Place>> extractedPlaces = placesExtractor.extractPlacesFrom(tweets)
        then:
            Awaitility.await().atMost(ONE_SECOND)until({
                assert extractedPlaces.size() == 3
                assert !extractedPlaces[3]?.present
            })
    }

    class ByTweetIdPlacesExtractor implements PlaceExtractor {

        @Override
        Optional<Place> extractPlaceFrom(Object parsedTweet) {
            if (parsedTweet.id_str == '3' ){
                return Optional.empty()
            }
            return Optional.of(new Place(new Place.PlaceDetails("Warsaw${parsedTweet.id_str}", 'PL'), origin, placeResolutionProbability))
        }

        @Override
        String getOrigin() {
            return this.class.simpleName
        }

        @Override
        PlaceExtractor.PlaceResolutionProbability getPlaceResolutionProbability() {
            return PlaceExtractor.PlaceResolutionProbability.LOW
        }
    }

}
