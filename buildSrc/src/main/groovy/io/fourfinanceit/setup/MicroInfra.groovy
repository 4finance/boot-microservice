package io.fourfinanceit.setup

import groovy.transform.Immutable
import groovy.transform.ToString
import org.gradle.api.Project

@Immutable
@ToString(ignoreNulls = true, includeNames = true)
class MicroInfra {
    String graphiteHost
    String metricsAppName
    String stubsRepositoryUrl
    boolean useStubsInSeparateJars

    void process(Map appPropsEntries) {
        appPropsEntries['graphite.host'] = graphiteHost
        appPropsEntries['metrics.path.app'] = metricsAppName
        appPropsEntries['stubrunner.stubs.repository.root'] = stubsRepositoryUrl
        appPropsEntries['stubrunner.use-microservice-definitions'] = useStubsInSeparateJars
    }
}
