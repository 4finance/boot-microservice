package com.ofg.twitter.controller.place.extractor

import com.ofg.twitter.controller.place.Place
import com.ofg.twitter.controller.place.Place.PlaceDetails
import com.ofg.twitter.controller.place.extractor.PlaceExtractor.PlaceResolutionProbability
import com.ofg.twitter.controller.place.extractor.metrics.MatchProbabilityMetrics
import groovy.transform.PackageScope

@PackageScope
class CoordinatesSectionExtractor implements PlaceExtractor {

    public static final String PLACE_EXTRACTION_NAME = 'twitter_coordinates_section'

    private final CityFinder cityFinder
    private final MatchProbabilityMetrics metrics

    public CoordinatesSectionExtractor(CityFinder cityFinder, MatchProbabilityMetrics matchProbabilityMetrics) {
        this.cityFinder = cityFinder
        this.metrics = matchProbabilityMetrics
    }

    @Override
    Optional<Place> extractPlaceFrom(Object parsedTweet) {
        if (parsedTweet.coordinates == null) {
            return Optional.empty()
        }
        def(long longitude, long latitude) = parsedTweet.coordinates.coordinates
        Optional<PlaceDetails> placeDetails = cityFinder.findCityFromCoordinates(longitude, latitude)
        return placeIfPresentOrEmptyOptional(placeDetails)
    }

    private Optional<Place> placeIfPresentOrEmptyOptional(Optional<PlaceDetails> placeDetails) {
        if (placeDetails.isPresent()) {
            metrics.update(placeResolutionProbability)
            return Optional.of(new Place(placeDetails.get(), origin, placeResolutionProbability))
        } else {
            return Optional.empty()
        }
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
