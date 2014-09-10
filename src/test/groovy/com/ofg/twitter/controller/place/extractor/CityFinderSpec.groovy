package com.ofg.twitter.controller.place.extractor

import com.ofg.infrastructure.web.resttemplate.custom.RestTemplate
import com.ofg.twitter.controller.place.Place
import spock.lang.Specification

class CityFinderSpec extends Specification {

    RestTemplate restTemplate = Stub()
    CityFinder cityFinder = new CityFinder(restTemplate, 'someUrl')

    def 'should find city by providing its coordinates'() {
        given:
            long longitude = -77.119759
            long latitude = 38.791645
            restTemplate.getForObject(_ as String, String) >> WeatherApiResponses.CITY_FOUND
        when:
            Optional<Place.PlaceDetails> foundCity = cityFinder.findCityFromCoordinates(latitude, longitude)
        then:
            foundCity.present
            foundCity.get().countryCode == 'US'
            foundCity.get().name == 'Tappahannock'
    }

    def 'should return empty result when city not found by its coordinates'() {
        given:
            long longitude = -77.119759
            long latitude = 38.791645
            restTemplate.getForObject(_ as String, String) >> WeatherApiResponses.CITY_NOT_FOUND
        when:
            Optional<Place.PlaceDetails> foundCity = cityFinder.findCityFromCoordinates(latitude, longitude)
        then:
            !foundCity.present
    }

    def 'should return true if city was found by its name'() {
        given:
            String cityToFind = 'Tappahannock'
            restTemplate.getForObject(_ as String, String) >> WeatherApiResponses.CITY_FOUND
        when:
            boolean cityFound = cityFinder.isCityExistent(cityToFind)
        then:
            cityFound
    }

    def 'should return false if city was not found by its name'() {
        given:
            String cityToFind = 'Tappahannock'
            restTemplate.getForObject(_ as String, String) >> WeatherApiResponses.CITY_NOT_FOUND
        when:
            boolean cityFound = cityFinder.isCityExistent(cityToFind)
        then:
            !cityFound
    }

}
