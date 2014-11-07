package com.ofg.microservice.config

import com.ofg.infrastructure.config.EnableMicroservice
import com.ofg.infrastructure.config.EnableSwaggerDocumentation
import org.springframework.context.annotation.Configuration

@Configuration
@EnableMicroservice
@EnableSwaggerDocumentation
class WebApplicationConfiguration {

}
