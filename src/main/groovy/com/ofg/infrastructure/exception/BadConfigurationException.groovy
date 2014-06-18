package com.ofg.infrastructure.exception

import groovy.transform.TypeChecked

@TypeChecked
class BadConfigurationException extends RuntimeException {
    BadConfigurationException(String message) {
        super(message)
    }
}
