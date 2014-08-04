package com.ofg.twitter.controller.place.extractor

import com.ofg.twitter.controller.place.Place
import groovy.transform.PackageScope

import static com.ofg.twitter.controller.place.extractor.PlaceExtractor.PlaceResolutionProbability.HIGH

@PackageScope
class CoordinatesSectionExtractor implements PlaceExtractor {

    public static final String PLACE_EXTRACTION_NAME = 'twitter_coordinates_section'

    private final CityFinder cityFinder

    public CoordinatesSectionExtractor(CityFinder cityFinder) {
        this.cityFinder = cityFinder
    }

    @Override
    Optional<Place> extractPlaceFrom(Object parsedTweet) {
        if (parsedTweet.coordinates == null) {
            return Optional.empty()
        }
        def(long longitude, long latitude) = parsedTweet.coordinates.coordinates
        Optional<Place.PlaceDetails> placeDetails = cityFinder.findCityFromCoordinates(longitude, latitude)
        if (!placeDetails.isPresent()) {
            return Optional.empty()
        }
        return Optional.of(new Place(placeDetails.get(), origin, placeResolutionProbability))
    }

    @Override
    String getOrigin() {
        return PLACE_EXTRACTION_NAME
    }

    @Override
    PlaceExtractor.PlaceResolutionProbability getPlaceResolutionProbability() {
        return HIGH
    }
}
