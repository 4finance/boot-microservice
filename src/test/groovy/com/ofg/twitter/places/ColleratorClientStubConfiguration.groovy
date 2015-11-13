package com.ofg.twitter.places
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import com.ofg.twitter.place.extractor.ColleratorClient
import com.ofg.twitter.place.extractor.WeatherApiResponses
import com.ofg.twitter.place.extractor.WeatherClient
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@CompileStatic
@Configuration
class ColleratorClientStubConfiguration {

    @Bean @Primary
    ColleratorClient colleratorClientStub(ServiceRestClient serviceRestClient) {
        return new ColleratorClientStub(serviceRestClient)
    }

    @Bean @Primary
    WeatherClient weatherClient(ServiceRestClient serviceRestClient, @Value('${city.finding.service.url:http://api.openweathermap.org/data/2.5/weather}') String cityFindingServiceUrl) {
        return new WeatherClient(serviceRestClient, cityFindingServiceUrl) {
            @Override
            String findCity(double latitude, double longitude) {
                return WeatherApiResponses.CITY_FOUND
            }
        }
    }
}
