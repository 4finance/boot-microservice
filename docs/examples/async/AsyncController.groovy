package com.ofg.microservice.examples
import com.ofg.microservice.infrastructure.correlationid.CorrelationIdHolder
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import groovy.transform.PackageScope
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import java.util.concurrent.Callable

@TypeChecked
@Controller
@Slf4j
@Api(value = "quotes", description = "Async quotes call to test correlation id")
//TODO: set up AsyncTaskExecutor, as the default doesn't reuse threads
class AsyncController {
    private AsyncService asyncService = new AsyncService()

    @RequestMapping(value = "/quotes", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    @ApiOperation(value = "Async call with CorrelationID",
            notes = "This will asynchronously call the server, proving that correlation id works in that case")
    Callable<String> callReturnigCorrelationIdAndUsingItInAsync() {
        String correlationId = CorrelationIdHolder.get()
        log.debug("Correlation id present in main request thread: $correlationId")
        return { asyncService.asyncWork() }
    }
}

@Slf4j
@PackageScope class AsyncService {
    String asyncWork() {
        String correlationId = CorrelationIdHolder.get()
        log.debug("Correlation id present in async thread: $correlationId")
        return "Watch the logs, and you will see all the threading magic happening there. " +
               "Also, you should see a header with correlationId in response."
    }
}