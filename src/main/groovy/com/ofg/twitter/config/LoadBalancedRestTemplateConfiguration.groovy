package com.ofg.twitter.config

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class LoadBalancedRestTemplateConfiguration {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new com.ofg.infrastructure.web.resttemplate.custom.RestTemplate(4096)
    }
}
