package com.ofg.microservice.config.web

import com.github.tomakehurst.wiremock.WireMockServer
import groovy.transform.TypeChecked

@TypeChecked
class HttpMockServer extends WireMockServer {

    static final int DEFAULT_PORT = 8030

    HttpMockServer(int port) {
        super(port)
    }

    HttpMockServer() {
        super(DEFAULT_PORT)
    }
    
    void shutdownServer() {
        if (isRunning()) {
            stop()
        }
        shutdown()
    }
}
