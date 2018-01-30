package com.ofg.twitter.places.contracts

import com.ofg.twitter.place.PairIdController
import com.ofg.twitter.place.extractor.PropagationWorker
import io.restassured.module.mockmvc.RestAssuredMockMvc
import spock.lang.Specification

abstract class BaseMockMvcSpec extends Specification {

    def setup() {
        RestAssuredMockMvc.standaloneSetup(new PairIdController(createAndStubPropagationWorker()))
    }

    private PropagationWorker createAndStubPropagationWorker() {
        return Mock(PropagationWorker)
    }
}
