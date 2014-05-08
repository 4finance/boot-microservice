package com.ofg.conf

import groovy.transform.TypeChecked

@TypeChecked
class Profiles {
    // public modifier required because of http://jira.codehaus.org/browse/GROOVY-3278
    public static final String PRODUCTION = 'prod'
    public static final String TEST = 'test'
    public static final String DEVELOPMENT = 'dev'

    static List<String> all() {
        return [PRODUCTION, TEST, DEVELOPMENT]
    }
}