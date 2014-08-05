package com.ofg.microservice.config

import com.ofg.infrastructure.web.config.SwaggerConfiguration
import com.ofg.infrastructure.web.config.WebConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import([WebConfiguration, SwaggerConfiguration])
class WebAppConfiguration {
}
