package com.ofg.twitter.controller.place.extractor

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