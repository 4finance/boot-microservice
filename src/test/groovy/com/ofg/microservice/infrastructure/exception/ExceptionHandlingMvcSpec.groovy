package com.ofg.microservice.infrastructure.exception
import com.ofg.base.MicroserviceMvcIntegrationSpec

import static org.hamcrest.Matchers.equalTo
import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ExceptionHandlingMvcSpec extends MicroserviceMvcIntegrationSpec {
    
    def "should return bad request error for missing last name"() {
        expect:
            mockMvc.perform(post("/test").contentType(APPLICATION_JSON)
                .content('{}'))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath('$[0].field', equalTo("shouldBeTrue")))
                .andExpect(jsonPath('$[0].message', equalTo("must be true")))
    }
    
}
