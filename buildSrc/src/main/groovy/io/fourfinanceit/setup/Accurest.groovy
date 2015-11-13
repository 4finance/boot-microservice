package io.fourfinanceit.setup

import groovy.transform.CompileStatic
import groovy.transform.ToString
import org.apache.commons.io.FileUtils
import org.gradle.api.Project

@ToString(ignoreNulls = true, includeNames = true)
@CompileStatic
class Accurest {
    final Project project
    final PackageMover packageMover
    final String accurestRoot
    final String accurestBaseClass
    final boolean changed

    Accurest(Project project, String accurestRoot, String accurestBaseClass, boolean changed) {
        this.project = project
        this.packageMover = new PackageMover(project)
        this.accurestRoot = accurestRoot
        this.accurestBaseClass = accurestBaseClass
        this.changed = changed
    }

    void process(Properties props) {
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
