package com.ofg.twitter.place

import com.ofg.twitter.place.extractor.CityFinder
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.constraints.NotNull

import static org.springframework.web.bind.annotation.RequestMethod.POST

@Slf4j
@RestController
@RequestMapping('/city')
@Api(value = "city", description = "Operations on cities")
class CityFindController {

    @Autowired private CityFinder cityFinder

    @RequestMapping(
            value = '/{lat}/{lon}',
            method = POST,
            consumes = 'application/vnd.com.ofg.twitter-places-analyzer.v1+json',
            produces = 'application/vnd.com.ofg.twitter-places-analyzer.v1+json')
    @ApiOperation(value = "Gets city name from the coordinates",
            notes = "The code calls openweather to get the city")
    String findCity(@PathVariable("lat") @NotNull Double lat, @PathVariable("lon") @NotNull Double lon) {
        def city = cityFinder.findCityFromCoordinates(lat, lon)
        log.info("Lat: ${lat}, Lon: ${lon} = city: ${city}")
        return city.present ? city.get().name : null
    }
}
