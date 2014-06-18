package com.ofg.infrastructure.discovery

import com.ofg.infrastructure.exception.BadConfigurationException
import groovy.json.JsonSlurper

class ServiceConfigurationResolver {

    final String basePath
    private final Object parsedConfiguration

    ServiceConfigurationResolver(String configuration) throws BadConfigurationException {
        (basePath, parsedConfiguration) = parseConfig(configuration)
    }

    private static List parseConfig(String config) {
        Map json = new JsonSlurper().parseText(config)
        if (json.size() != 1) {
            throw new BadConfigurationException('Microservice configuration should have exactly one root element')
        }
        String basePath = json.keySet().first()
        return [basePath, json[basePath]]
    }

    String getMicroserviceName() {
        return parsedConfiguration.this
    }
    
    Map<String, String> getDependencies() {
        return parsedConfiguration.dependencies
    }
} 
