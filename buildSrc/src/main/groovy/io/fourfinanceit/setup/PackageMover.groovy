package io.fourfinanceit.setup

import groovy.io.FileType
import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import org.apache.commons.io.FileUtils
import org.gradle.api.Project

@PackageScope
@CompileStatic
class PackageMover {

    private final Project project

    PackageMover(Project project) {
        this.project = project
    }

    void changePackagesInFiles(String currentPath, String oldPackage, String newPackage) {
        project.file(currentPath).eachFileRecurse(FileType.FILES) { File file ->
            file.text = file.text.replaceAll(oldPackage, newPackage)
        }
    }

    void moveDirectoriesToNewPackage(String currentPath, File newFolder) {
        project.file(currentPath).listFiles({ File pathname -> pathname.isDirectory() } as FileFilter).each { File file ->
            FileUtils.moveDirectoryToDirectory(file, newFolder, true)
        }
    }
}
