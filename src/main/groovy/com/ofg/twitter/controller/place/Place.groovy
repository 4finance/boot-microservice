package com.ofg.twitter.controller.place

import com.ofg.twitter.controller.place.extractor.PlaceExtractor
import groovy.transform.ToString

@ToString
class Place {
    final PlaceDetails placeDetails
    final String placeResolutionOrigin
    final int placeResolutionProbability

    Place(PlaceDetails placeDetails, String placeResolutionOrigin, PlaceExtractor.PlaceResolutionProbability placeResolutionProbability) {
        this.placeDetails = placeDetails
        this.placeResolutionOrigin = placeResolutionOrigin
        this.placeResolutionProbability = placeResolutionProbability.ordinal()
    }

    @ToString
    static class PlaceDetails {
        final String name
        final String countryCode

        PlaceDetails(String name, String countryCode) {
            this.name = name
            this.countryCode = countryCode
        }
    }

}
