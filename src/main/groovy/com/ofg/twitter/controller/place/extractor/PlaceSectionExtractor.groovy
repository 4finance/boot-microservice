package com.ofg.twitter.controller.place.extractor

import com.ofg.twitter.controller.place.Place
import groovy.transform.PackageScope

@PackageScope
class PlaceSectionExtractor implements PlaceExtractor {

    public static final String PLACE_EXTRACTION_NAME = 'twitter_place_section'

    @Override
    Optional<Place> extractPlaceFrom(Object parsedTweet) {
        if(parsedTweet.place == null) {
            return Optional.empty()
        }
        Optional.of(new Place(new Place.PlaceDetails(parsedTweet.place.name, parsedTweet.place.country_code), origin, placeResolutionProbability))
    }

    @Override
    String getOrigin() {
        return PLACE_EXTRACTION_NAME
    }

    @Override
    PlaceExtractor.PlaceResolutionProbability getPlaceResolutionProbability() {
        return PlaceExtractor.PlaceResolutionProbability.HIGH
    }
}
