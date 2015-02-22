package com.ofg.twitter.places.accurest

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc
import com.ofg.twitter.place.PairIdController
import com.ofg.twitter.place.extractor.PropagationWorker
import spock.lang.Specification

abstract class BaseMockMvcSpec extends Specification {

    def setup() {
        RestAssuredMockMvc.standaloneSetup(new PairIdController(createAndStubPropagationWorker()))
    }

    private PropagationWorker createAndStubPropagationWorker() {
        return Mock(PropagationWorker)
    }
}
