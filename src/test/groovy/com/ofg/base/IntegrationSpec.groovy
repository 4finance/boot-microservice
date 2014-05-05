package com.ofg.base

import com.ofg.conf.Application
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = [Application], loader = SpringApplicationContextLoader)
@WebAppConfiguration
@ActiveProfiles(["test"]) //WARNING: cannot use Profiles class here, thought this has to equal to Profiles.TEST, because: http://jira.codehaus.org/browse/GROOVY-3278
abstract class IntegrationSpec extends Specification {
}
