package io.fourfinanceit.setup

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.transform.ToString
import org.apache.commons.io.FileUtils
import org.gradle.api.Project

@ToString(ignoreNulls = true, includeNames = true)
class Maven {
    public static final String DEFAULT_PACKAGE = 'com.ofg'
    final Project project
    final String mavenUser
    final String mavenPassword
    final String mavenRepoUrl
    final String mavenProjectGroupId
    final String mavenArtifactId
    final PackageMover packageMover

    Maven(Project project, String mavenUser, String mavenPassword, String mavenRepoUrl, String mavenProjectGroupId, String mavenArtifactId) {
        this.project = project
        this.mavenUser = mavenUser
        this.mavenPassword = mavenPassword
        this.mavenRepoUrl = mavenRepoUrl
        this.mavenProjectGroupId = mavenProjectGroupId
        this.mavenArtifactId = mavenArtifactId
        this.packageMover = new PackageMover(project)
    }

    void process(Properties props) {
        props.put('mavenUser', mavenUser)
        props.put('mavenPassword', mavenPassword)
        props.put('mavenRepoUrl', mavenRepoUrl)
        props.put('mavenProjectGroupId', mavenProjectGroupId)
        props.put('mavenArtifactId', mavenArtifactId)
        updateSettingsGradle(project)
        updateMicroserviceJson(project)
        changeAccurestRootPathToIncludeNewPackage()
    }

    private void changeAccurestRootPathToIncludeNewPackage() {
        if( mavenArtifactId != DEFAULT_PACKAGE) {
            File rootFolder = project.file("repository/mappings")
            File newFolder = new File(rootFolder, mavenArtifactId.replaceAll('\\.', '/'))
            packageMover.moveDirectoriesToNewPackage("repository/mappings/com/ofg", newFolder)
            FileUtils.deleteDirectory(project.file("repository/mappings/com"))
        }
    }

    private void updateSettingsGradle(Project project) {
        File settingsGradle = project.file('settings.gradle')
        settingsGradle.text = "rootProject.name = '$mavenArtifactId'"
    }

    private void updateMicroserviceJson(Project project) {
        File microserviceJson = project.file('src/main/resources/microservice.json')
        def microserviceDescriptor = new JsonSlurper().parse(microserviceJson)
        microserviceDescriptor.pl.this = microserviceDescriptor.pl.this.replaceAll('com/ofg', mavenProjectGroupId.replaceAll('\\.', '/'))
        microserviceJson.text = JsonOutput.prettyPrint(JsonOutput.toJson(microserviceDescriptor))
    }
}
