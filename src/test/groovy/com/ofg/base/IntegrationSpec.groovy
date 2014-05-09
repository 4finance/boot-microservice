package com.ofg.base
import com.ofg.config.Application
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

import static com.ofg.config.Profiles.TEST

@ContextConfiguration(classes = [Application], loader = SpringApplicationContextLoader)
@WebAppConfiguration
@ActiveProfiles([TEST]) //WARNING: cannot use Profiles class here, thought this has to equal to Profiles.TEST, because: http://jira.codehaus.org/browse/GROOVY-3278
abstract class IntegrationSpec extends Specification {
}
