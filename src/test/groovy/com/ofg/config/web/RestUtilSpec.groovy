package com.ofg.config.web

import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Unroll

class RestUtilSpec extends Specification {

    @Unroll
    def 'should return [#isError] if http status series equals [#httpStatusSeries]'() {
        expect:
            RestUtil.isError(httpStatus) == isError
        where:
            httpStatus                          || isError
            HttpStatus.ACCEPTED                 || false
            HttpStatus.UNAUTHORIZED             || true
            HttpStatus.INTERNAL_SERVER_ERROR    || true
    }

}
