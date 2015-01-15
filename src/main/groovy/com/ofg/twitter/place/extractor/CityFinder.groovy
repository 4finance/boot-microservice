package com.ofg.twitter.place.extractor

import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus

@Slf4j
class CityFinder {

    private final WeatherClient weatherClient

    CityFinder(WeatherClient weatherClient) {
        this.weatherClient = weatherClient
    }

    @Cacheable('cities')
    Optional<Place.PlaceDetails> findCityFromCoordinates(double latitude, double longitude) {
        log.debug("Geolocating: ($latitude, $longitude)")
        String cityResponse = weatherClient.findCity(latitude, longitude)
        def parsedCityResponse = new JsonSlurper().parseText(cityResponse)
        if (!isStatusResponseOk(parsedCityResponse)) {
            return Optional.empty()
        }
        return Optional.<Place.PlaceDetails>of(new Place.PlaceDetails(parsedCityResponse.name, parsedCityResponse.sys.country))
    }

    boolean isCityExistent(String cityNameToCheck) {
        String cityResponse = weatherClient.isCityExistent(cityNameToCheck)
        def parsedCityResponse = new JsonSlurper().parseText(cityResponse)
        return isStatusResponseOk(parsedCityResponse)
    }

    private boolean isStatusResponseOk(def parsedCityResponse) {
        return parsedCityResponse.cod.toInteger() == HttpStatus.OK.value()
    }
}
