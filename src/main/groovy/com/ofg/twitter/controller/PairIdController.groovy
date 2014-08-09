package com.ofg.twitter.controller
import com.ofg.twitter.controller.place.extractor.PropagationWorker
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.constraints.NotNull
import java.util.concurrent.Callable

import static org.springframework.web.bind.annotation.RequestMethod.PUT

@Slf4j
@RestController
@RequestMapping('/api')
@Api(value = "pairId", description = "Collects places from tweets and propagates them to Collerators")
class PairIdController {

    @Autowired private PropagationWorker propagationWorker

    
    @RequestMapping(
            value = '{pairId}',
            method = PUT,
            consumes = 'application/vnd.com.ofg.twitter-places-analyzer.v1+json',
            produces = 'application/vnd.com.ofg.twitter-places-analyzer.v1+json')
    @ApiOperation(value = "Async collecting and propagating of tweets for a given pairId",
            notes = "This will asynchronously call tweet collecting, place extracting and their propagation to Collerators")
    Callable<Void> getPlacesFromTweets(@PathVariable @NotNull long pairId, @RequestBody @NotNull String tweets) {
        propagationWorker.collectAndPropagate(pairId, tweets)
    }

}
