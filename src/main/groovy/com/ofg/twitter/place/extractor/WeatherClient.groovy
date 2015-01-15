package com.ofg.twitter.place.extractor

import com.netflix.hystrix.HystrixCommand
import com.netflix.hystrix.HystrixCommandGroupKey
import com.netflix.hystrix.HystrixCommandKey
import com.ofg.infrastructure.hystrix.CorrelatedCommand
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
        withHystrix('findCity') {
            return serviceRestClient.forExternalService()
                    .get()
                    .onUrl(cityFindingServiceUrl + "?lat=${latitude}&lon=${longitude}")
                    .anObject()
                    .ofType(String)
        }
    }

    String isCityExistent(String cityNameToCheck) {
        withHystrix('isCityExistent') {
            return serviceRestClient.forExternalService()
                    .get()
                    .onUrl("$cityFindingServiceUrl?q=$cityNameToCheck")
                    .anObject()
                    .ofType(String)
        }
    }

    private def withHystrix(String name, Closure block) {
        return new CorrelatedCommand(key(name)) {
            @Override
            def doRun() {
                return block.call()
            }
        }.execute()
    }

    private def HystrixCommand.Setter key(String name) {
        return HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(WeatherClient.class.getSimpleName()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(name))
    }
}
