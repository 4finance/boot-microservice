package com.ofg.conf

import groovy.transform.TypeChecked

@TypeChecked
class Profiles {
    static final String PRODUCTION = "prod"
    static final String TEST = "test"
    static final String DEVELOPMENT = "dev"

    static List<String> all() {
        return [PRODUCTION, TEST, DEVELOPMENT]
    }
}