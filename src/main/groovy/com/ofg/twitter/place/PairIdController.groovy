package com.ofg.twitter.place

import com.ofg.twitter.place.extractor.PropagationWorker
import com.ofg.twitter.place.model.Tweet
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.fourfinance.activity_tracker.activity.TrackUserActivity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.constraints.NotNull
import java.util.concurrent.Callable

import static com.ofg.twitter.config.Versions.TWITTER_PLACES_ANALYZER_JSON_VERSION_1
import static org.springframework.web.bind.annotation.RequestMethod.PUT

@Slf4j
@RestController
@RequestMapping('/api')
@TypeChecked
@Api(value = "pairId", description = "Collects places from tweets and propagates them to Collerators")
class PairIdController {

    private final PropagationWorker propagationWorker

    @Autowired PairIdController(PropagationWorker propagationWorker) {
        this.propagationWorker = propagationWorker
    }

    @RequestMapping(
            value = '{pairId}',
            method = PUT,
            consumes = TWITTER_PLACES_ANALYZER_JSON_VERSION_1,
            produces = TWITTER_PLACES_ANALYZER_JSON_VERSION_1)
    @ApiOperation(value = "Async collecting and propagating of tweets for a given pairId",
            notes = "This will asynchronously call tweet collecting, place extracting and their propagation to Collerators")
    @TrackUserActivity("get places from tweets")
    Callable<Void> getPlacesFromTweets(@PathVariable @NotNull @ApiParam(defaultValue = "0") long pairId, @RequestBody @NotNull List<Tweet> tweets) {
        return {
            propagationWorker.collectAndPropagate(pairId, tweets)
        }
    }

}
