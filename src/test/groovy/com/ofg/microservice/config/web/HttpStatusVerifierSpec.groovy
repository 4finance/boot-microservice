package com.ofg.microservice.config.web
import com.ofg.microservice.config.web.errors.HttpStatusVerifier
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Unroll

class HttpStatusVerifierSpec extends Specification {

    @Unroll
    def 'should return [#isError] if http status series equals [#httpStatusSeries]'() {
        expect:
            HttpStatusVerifier.isError(httpStatus) == isError
        where:
            httpStatus                          || isError
            HttpStatus.ACCEPTED                 || false
            HttpStatus.UNAUTHORIZED             || true
            HttpStatus.INTERNAL_SERVER_ERROR    || true
    }

}
