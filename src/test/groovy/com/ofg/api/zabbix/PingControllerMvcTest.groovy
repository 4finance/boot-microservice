package com.ofg.api.zabbix
import com.ofg.base.MvcIntegrationTest
import org.junit.Test
import org.springframework.http.MediaType

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class PingControllerMvcTest extends MvcIntegrationTest {
    @Test
    void "should return OK on ping for Zabbix"() {
        mockMvc.perform(get('/ping').accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string('OK'))
    }
}
