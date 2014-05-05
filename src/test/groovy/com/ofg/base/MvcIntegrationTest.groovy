package com.ofg.base
import com.ofg.conf.Application
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = [Application])
@ActiveProfiles('test') //WARNING: cannot use Profiles class here, thought this has to equal to Profiles.TEST, because: http://jira.codehaus.org/browse/GROOVY-3278
abstract class MvcIntegrationTest {
    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired private ApplicationContext applicationContext;
    protected MockMvc mockMvc;

    @Before
    void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}