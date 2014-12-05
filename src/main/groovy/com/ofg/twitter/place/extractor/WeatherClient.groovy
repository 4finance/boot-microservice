package com.ofg.twitter.place.extractor

import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import groovy.transform.CompileStatic

@CompileStatic
class WeatherClient {

    private final ServiceRestClient serviceRestClient
    private final String cityFindingServiceUrl

    WeatherClient(ServiceRestClient serviceRestClient, String cityFindingServiceUrl) {
        this.serviceRestClient = serviceRestClient
        this.cityFindingServiceUrl = cityFindingServiceUrl
    }

    String findCity(double latitude, double longitude) {
        return serviceRestClient.forExternalService()
                .get()
                .onUrl("$cityFindingServiceUrl?lat=${latitude}&lon=${longitude}")
                .anObject()
                .ofType(String)
    }

    String isCityExistent(String cityNameToCheck) {
        return serviceRestClient.forExternalService()
                .get()
                .onUrl("$cityFindingServiceUrl?q=$cityNameToCheck")
                .anObject()
                .ofType(String)
    }


}
