package com.ofg.twitter.geb

import com.ofg.twitter.Application
import geb.spock.GebSpec
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = Application)
@WebAppConfiguration
@IntegrationTest("spring.profiles.active:dev,springCloud")
abstract class BaseBootGebSpec extends GebSpec {

    def setupSpec() {
        System.setProperty("APP_ENV", "prod");
        System.setProperty("CONFIG_FOLDER", "properties");
        System.setProperty("encrypt.key", "secretEncryptKey");
    }
}
