package com.ofg.api.exception

import com.ofg.base.MvcIntegrationTest
import groovy.transform.TypeChecked
import org.junit.Test
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

import static org.hamcrest.Matchers.equalTo
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@TypeChecked
class ExceptionHandlingMvcTest extends MvcIntegrationTest {
    @Test
    public void shouldReturnBadRequestErrorForMissingLastName() {
        mockMvc.perform(post("/test").contentType(MediaType.APPLICATION_JSON)
                .content('{}'))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath('$[0].field', equalTo("shouldBeTrue")))
                .andExpect(jsonPath('$[0].message', equalTo("must be true")))
    }
}
