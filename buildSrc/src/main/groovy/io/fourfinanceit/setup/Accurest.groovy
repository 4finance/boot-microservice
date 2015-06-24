package io.fourfinanceit.setup

import groovy.transform.Immutable
import groovy.transform.ToString
import org.apache.commons.io.FileUtils
import org.gradle.api.Project

@Immutable
@ToString(ignoreNulls = true, includeNames = true)
class Accurest {
    String accurestRoot
    String accurestBaseClass
    boolean changed

    void process(Project project, Properties props) {
        props.put('accurestRoot', accurestRoot)
        props.put('accurestBaseClass', accurestBaseClass)
        if (changed) {
            migrateRootAccurestPackage(project)
        }
    }

    private void migrateRootAccurestPackage(Project project) {
        FileUtils.moveDirectoryToDirectory(project.file("repository/mappings"), project.file(accurestRoot), true)
    }
}
