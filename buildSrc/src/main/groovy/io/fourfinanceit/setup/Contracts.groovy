package io.fourfinanceit.setup

import groovy.transform.CompileStatic
import groovy.transform.ToString
import org.apache.commons.io.FileUtils
import org.gradle.api.Project

@ToString(ignoreNulls = true, includeNames = true)
@CompileStatic
class Contracts {
    final Project project
    final PackageMover packageMover
    final String contractsRoot
    final String contractsBaseClass
    final boolean changed

    Contracts(Project project, String contractsRoot, String contractsBaseClass, boolean changed) {
        this.project = project
        this.packageMover = new PackageMover(project)
        this.contractsRoot = contractsRoot
        this.contractsBaseClass = contractsBaseClass
        this.changed = changed
    }

    void process(Properties props) {
        props.put('contractsRoot', contractsRoot)
        props.put('contractsBaseClass', contractsBaseClass)
        if (changed) {
            migrateRootContractsPackage(project)
        }
    }

    private void migrateRootContractsPackage(Project project) {
        FileUtils.moveDirectoryToDirectory(project.file("src/test/resources/contracts"), project.file(contractsRoot), true)
    }
}
