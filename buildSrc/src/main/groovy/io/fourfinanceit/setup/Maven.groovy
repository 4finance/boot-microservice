package io.fourfinanceit.setup
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.transform.Immutable
import groovy.transform.ToString
import org.gradle.api.Project

@Immutable
@ToString(ignoreNulls = true, includeNames = true)
class Maven {
    String mavenUser
    String mavenPassword
    String mavenRepoUrl
    String mavenProjectGroupId
    String mavenArtifactId

    void process(Project project, Properties props) {
        props.put('mavenUser', mavenUser)
        props.put('mavenPassword', mavenPassword)
        props.put('mavenRepoUrl', mavenRepoUrl)
        props.put('mavenProjectGroupId', mavenProjectGroupId)
        props.put('mavenArtifactId', mavenArtifactId)
        updateSettingsGradle(project)
        updateMicroserviceJson(project)
    }

    private void updateSettingsGradle(Project project) {
        File settingsGradle = project.file('settings.gradle')
        settingsGradle.text = "rootProject.name='$mavenArtifactId'"
    }

    private void updateMicroserviceJson(Project project) {
        File microserviceJson = project.file('src/main/resources/microservice.json')
        def microserviceDescriptor = new JsonSlurper().parse(microserviceJson)
        microserviceDescriptor.pl.this = microserviceDescriptor.pl.this.replaceAll('com/ofg', mavenProjectGroupId.replaceAll('\\.', '/'))
        microserviceJson.text = JsonOutput.prettyPrint(JsonOutput.toJson(microserviceDescriptor))
    }
}
