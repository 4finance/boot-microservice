package com.ofg.base

import com.ofg.conf.Application
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

import static com.ofg.conf.Profiles.TEST

@ContextConfiguration(classes = [Application], loader = SpringApplicationContextLoader)
@WebAppConfiguration
@ActiveProfiles(TEST)
abstract class IntegrationSpec extends Specification {
}
