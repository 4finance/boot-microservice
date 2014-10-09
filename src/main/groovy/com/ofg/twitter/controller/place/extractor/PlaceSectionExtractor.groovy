package com.ofg.twitter.controller.place.extractor

import com.ofg.twitter.controller.place.Place
import com.ofg.twitter.controller.place.Place.PlaceDetails
import com.ofg.twitter.controller.place.extractor.PlaceExtractor.PlaceResolutionProbability
import com.ofg.twitter.controller.place.extractor.metrics.MatchProbabilityMetrics
import groovy.transform.PackageScope

@PackageScope
class PlaceSectionExtractor implements PlaceExtractor {

    public static final String PLACE_EXTRACTION_NAME = 'twitter_place_section'

    private final MatchProbabilityMetrics metrics

    PlaceSectionExtractor(MatchProbabilityMetrics matchProbabilityMetrics) {
        metrics = matchProbabilityMetrics
    }

    @Override
    Optional<Place> extractPlaceFrom(Object parsedTweet) {
        if(parsedTweet.place == null) {
            return Optional.empty()
        } else {
            metrics.update(placeResolutionProbability)
            return extractFromParsedPlace(parsedTweet.place)
        }
    }

    private Optional<Place> extractFromParsedPlace(place) {
        PlaceDetails placeDetails = new PlaceDetails(place.name, place.country_code)
        return Optional.of(new Place(placeDetails, origin, placeResolutionProbability))
    }

    @Override
    String getOrigin() {
        return PLACE_EXTRACTION_NAME
    }

    @Override
    PlaceResolutionProbability getPlaceResolutionProbability() {
        return PlaceResolutionProbability.HIGH
    }
}
