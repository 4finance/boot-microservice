package com.ofg.twitter.places

import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import com.ofg.twitter.controller.place.extractor.ColleratorClient
import groovy.transform.CompileStatic

import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference

@CompileStatic
class ColleratorClientStub extends ColleratorClient {

    final AtomicLong savedPairId = new AtomicLong()
    final AtomicReference<String> savedPlaces = new AtomicReference<>()

    ColleratorClientStub(ServiceRestClient serviceRestClient) {
        super(serviceRestClient)
    }

    @Override
    void populatePlaces(long pairId, String places) {
        savedPairId.set(pairId)
        savedPlaces.set(places)
        super.populatePlaces(pairId, places)
    }
}
