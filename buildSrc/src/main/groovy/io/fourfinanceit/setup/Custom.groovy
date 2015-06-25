package io.fourfinanceit.setup
import groovy.io.FileType
import groovy.transform.CompileStatic
import org.gradle.api.Project

@CompileStatic
class Custom {
    final Project project
    final PackageMover packageMover
    final String rootPackage
    final boolean changed

    Custom(Project project, String rootPackage, boolean changed) {
        this.project = project
        this.rootPackage = rootPackage
        this.changed = changed
        this.packageMover = new PackageMover(project)
    }

    void process(Accurest accurest) {
        if (!changed) {
            return
        }
        List folders = ['main', 'test']
        movePackages(folders, project, accurest)
    }

    private void movePackages(List<String> folders, Project project, Accurest accurest) {
        folders.each { String folder ->
            File rootFolder = project.file("src/$folder/groovy/")
            File newFolder = new File(rootFolder, rootPackage.replaceAll('\\.', '/'))
            packageMover.changePackagesInFiles("src/$folder/groovy/com/ofg", 'com.ofg.twitter', "${rootPackage}.twitter")
            packageMover.moveDirectoriesToNewPackage("src/$folder/groovy/com/ofg", newFolder)
        }
        movePackagesInAccurestContracts(accurest)
    }

    private movePackagesInAccurestContracts(Accurest accurest) {
        packageMover.changePackagesInFiles(accurest.accurestRoot, 'com.ofg.twitter', "${rootPackage}.twitter")
    }

}
