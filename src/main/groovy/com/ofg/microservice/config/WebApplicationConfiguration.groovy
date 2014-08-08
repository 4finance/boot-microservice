package com.ofg.microservice.config

import com.ofg.infrastructure.config.WebAppConfiguration
import com.ofg.infrastructure.web.config.SwaggerConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import([WebAppConfiguration, SwaggerConfiguration])
class WebApplicationConfiguration {
}
