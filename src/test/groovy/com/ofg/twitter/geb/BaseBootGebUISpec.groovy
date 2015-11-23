package com.ofg.twitter.geb

import com.ofg.stub.server.AvailablePortScanner
import com.ofg.twitter.Application
import geb.spock.GebSpec
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = Application)
@WebAppConfiguration
@DirtiesContext
abstract class BaseBootGebUISpec extends GebSpec {

    def setupSpec() {
        System.setProperty("APP_ENV", "dev")
        new AvailablePortScanner(9000, 9100).tryToExecuteWithFreePort { int port ->
            System.setProperty("server.port", String.valueOf(port))
            System.setProperty("geb.build.baseUrl", "http://localhost:${String.valueOf(port)}")
        }
        System.setProperty("CONFIG_FOLDER", "properties")
        System.setProperty("encrypt.key", "secretEncryptKey")
    }
}
