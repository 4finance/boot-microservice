package com.ofg.twitter.controller.place.extractor

import com.ofg.infrastructure.web.resttemplate.RestTemplate
import com.ofg.twitter.controller.place.Place
import groovy.json.JsonSlurper
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus

class CityFinder {

    private final RestTemplate restTemplate
    private final String cityFindingServiceUrl

    CityFinder(RestTemplate restTemplate, String cityFindingServiceUrl) {
        this.restTemplate = restTemplate
        this.cityFindingServiceUrl = cityFindingServiceUrl
    }

    @Cacheable('cities')
    Optional<Place.PlaceDetails> findCityFromCoordinates(long latitude, long longitude) {
        String cityResponse = restTemplate.getForObject("$cityFindingServiceUrl?lat=${latitude.toInteger()}&lon=${longitude.toInteger()}", String)
        def parsedCityResponse = new JsonSlurper().parseText(cityResponse)
        if (!isStatusResponseOk(parsedCityResponse)) {
            return Optional.empty()
        }
        return Optional.<Place.PlaceDetails>of(new Place.PlaceDetails(parsedCityResponse.name, parsedCityResponse.sys.country))
    }

    boolean isCityExistent(String cityNameToCheck) {
        String cityResponse = restTemplate.getForObject("$cityFindingServiceUrl?q=$cityNameToCheck", String)
        def parsedCityResponse = new JsonSlurper().parseText(cityResponse)
        return isStatusResponseOk(parsedCityResponse)
    }

    private boolean isStatusResponseOk(parsedCityResponse) {
        parsedCityResponse.cod.toInteger() == HttpStatus.OK.value()
    }
}
