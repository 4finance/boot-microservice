package com.ofg.conf.web
import groovy.transform.PackageScope
import groovy.transform.TypeChecked
import org.springframework.web.filter.Log4jNestedDiagnosticContextFilter

@TypeChecked
@PackageScope
class RequestBodyLoggingContextFilter extends Log4jNestedDiagnosticContextFilter {
    RequestBodyLoggingContextFilter(int maxPayloadLength) {
        this.includePayload = true
        this.maxPayloadLength = maxPayloadLength
    }
}
