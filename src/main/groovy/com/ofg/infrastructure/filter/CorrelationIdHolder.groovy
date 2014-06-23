package com.ofg.infrastructure.filter

import groovy.transform.PackageScope
import groovy.transform.TypeChecked

@TypeChecked
//from: https://github.com/daniel-bryant-uk/correlation-id-sync/
class CorrelationIdHolder {
    static final String CORRELATION_ID_HEADER = "correlationId"
    private static final ThreadLocal<String> id = new ThreadLocal<String>()

    @PackageScope static void setId(String correlationId) {
        id.set(correlationId)
    }

    static String getId() {
        return id.get()
    }
}
