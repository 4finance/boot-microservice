package com.ofg.twitter.controller.place.extractor

import com.ofg.twitter.controller.place.Place
import groovy.transform.PackageScope

@PackageScope
interface PlaceExtractor {

    Optional<Place> extractPlaceFrom(Object parsedTweet)

    String getOrigin()

    PlaceResolutionProbability getPlaceResolutionProbability()

    enum PlaceResolutionProbability {
        LOW, MEDIUM, HIGH
    }

}