package com.ofg.twitter.place.extractor

import groovy.transform.PackageScope
import groovy.transform.ToString

@ToString
@PackageScope
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
