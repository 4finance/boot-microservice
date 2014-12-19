package com.ofg.twitter

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.ofg.infrastructure.environment.EnvironmentSetupVerifier
import groovy.transform.TypeChecked
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.scheduling.annotation.EnableAsync

import static com.ofg.config.BasicProfiles.*

@TypeChecked
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
@EnableAsync
class Application {

    static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application)
        application.addListeners(new EnvironmentSetupVerifier([DEVELOPMENT, PRODUCTION, TEST]))
        application.run(args)
    }

    @Bean
    public MappingJackson2HttpMessageConverter jacksonConvertor(){
        MappingJackson2HttpMessageConverter convertor= new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        convertor.setObjectMapper(mapper);
        return convertor;
    }
}
