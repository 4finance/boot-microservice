package com.ofg.twitter.places

import org.springframework.boot.test.context.SpringBootTest

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(classes = ColleratorClientStubConfiguration, webEnvironment = RANDOM_PORT)
class AcceptanceSpec extends AbstractAcceptanceSpec {
}
