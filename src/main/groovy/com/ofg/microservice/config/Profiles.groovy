package com.ofg.microservice.config

import groovy.transform.TypeChecked

@TypeChecked
class Profiles {
    public static final String PRODUCTION = "prod"
    public static final String TEST = "test"
    public static final String DEVELOPMENT = "dev"

    static List<String> all() {
        return [PRODUCTION, TEST, DEVELOPMENT]
    }
}