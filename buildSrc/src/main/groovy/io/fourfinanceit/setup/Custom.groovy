package io.fourfinanceit.setup

import groovy.io.FileType
import groovy.transform.Immutable
import org.apache.commons.io.FileUtils
import org.gradle.api.Project

@Immutable
class Custom {
    String rootPackage
    boolean changed

    void process(Project project, Accurest accurest) {
        if (!changed) {
            return
        }
        List folders = ['main', 'test']
        movePackages(folders, project, accurest)
    }

    private void movePackages(ArrayList<String> folders, Project project, Accurest accurest) {
        folders.collect { String folder ->
            File rootFolder = project.file("src/$folder/groovy/")
            File newFolder = new File(rootFolder, rootPackage.replaceAll('\\.', '/'))
            changePackagesInFiles(project, folder)
            moveDirectoriesToNewPackage(project, folder, newFolder)
        }
        movePackagesInAccurestContracts(project, accurest)
    }

    private movePackagesInAccurestContracts(Project project, Accurest accurest) {
        project.file(accurest.accurestRoot).eachFileRecurse(FileType.FILES) { File file ->
            file.text = file.text.replaceAll('com.ofg.twitter', "${rootPackage}.twitter")
        }
    }

    private changePackagesInFiles(Project project, String folder) {
        project.file("src/$folder/groovy/com/ofg").eachFileRecurse(FileType.FILES) { File file ->
            file.text = file.text.replaceAll('com.ofg.twitter', "${rootPackage}.twitter")
        }
    }

    private void moveDirectoriesToNewPackage(Project project, String folder, File newFolder) {
        project.file("src/$folder/groovy/com/ofg").listFiles({ File pathname -> pathname.isDirectory() } as FileFilter).each {
            FileUtils.moveDirectoryToDirectory(it, newFolder, true)
        }
    }

}
