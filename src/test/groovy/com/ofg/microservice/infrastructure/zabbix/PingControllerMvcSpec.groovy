package com.ofg.microservice.infrastructure.zabbix
import com.ofg.base.MvcIntegrationSpec
import org.springframework.http.MediaType

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class PingControllerMvcSpec extends MvcIntegrationSpec {
    
    def "should return OK on ping for Zabbix"() {
        expect:
            mockMvc.perform(get('/ping').accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string('OK'))
    }
}
