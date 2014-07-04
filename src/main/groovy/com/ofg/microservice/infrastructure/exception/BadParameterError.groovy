package com.ofg.microservice.infrastructure.exception

import groovy.transform.TypeChecked

@TypeChecked
class BadParameterError {
    String field
    String message

    BadParameterError(field, message) {
        this.field = field
        this.message = message
    }
}
