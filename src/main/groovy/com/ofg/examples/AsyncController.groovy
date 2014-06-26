package com.ofg.examples

import com.ofg.infrastructure.correlationid.CorrelationIdHolder
import groovy.transform.PackageScope
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import java.util.concurrent.Callable

import static com.ofg.infrastructure.correlationid.CorrelationCallable.withCorrelationId

@TypeChecked
@Controller
@Slf4j
//TODO: set up AsyncTaskExecutor, as the default doesn't reuse threads
class AsyncController {
    private AsyncService asyncService = new AsyncService()

    @RequestMapping("/quotes")
    @ResponseBody
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