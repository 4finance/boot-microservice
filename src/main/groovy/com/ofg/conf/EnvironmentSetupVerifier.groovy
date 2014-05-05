package com.ofg.conf

import groovy.transform.TypeChecked
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent
import org.springframework.context.ApplicationListener

@TypeChecked
class EnvironmentSetupVerifier implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        String[] activeProfiles = event.environment.activeProfiles
        if (activeProfiles.length == 0) {
            println """\
            This app requires an explicit profile
            Please setup a profile in environment variable 'spring.profiles.active'
            or pass -Dspring.profiles.active=NAME_OF_PROFILE as a JVM param
            Possible profiles: ${Profiles.all().join(", ")}""".stripIndent()
            System.exit(1)
        }
        println "Application is run with these active profiles: " + activeProfiles.join(", ")
    }
}
