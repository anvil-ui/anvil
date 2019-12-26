package dev.inkremental.meta.gradle

import org.gradle.api.artifacts.transform.*
import org.gradle.api.file.FileSystemLocation
import org.gradle.api.file.FileType
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.work.ChangeType
import org.gradle.work.InputChanges
import java.util.zip.ZipFile
import javax.inject.Inject

@Suppress("UnstableApiUsage")
abstract class AarToJarTransform :
    TransformAction<TransformParameters.None> {
    @get:Inject
    abstract val inputChanges: InputChanges

    @get:InputArtifact
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val input: Provider<FileSystemLocation>

    override fun transform(outputs: TransformOutputs) {
        inputChanges.getFileChanges(input).forEach { change ->
            val changedFile = change.file
            val outFile = outputs.file("${changedFile.nameWithoutExtension}.jar")
            if (change.fileType != FileType.FILE) {
                return@forEach
            }
            when (change.changeType) {
                ChangeType.ADDED, ChangeType.MODIFIED -> {
                    outFile.parentFile.mkdirs()
                    ZipFile(changedFile).use { zipFile ->
                        zipFile.entries()
                            .toList()
                            .first { it.name == "classes.jar" }
                            .let(zipFile::getInputStream)
                            .use { input ->
                                outFile.outputStream().use { output ->
                                    input.copyTo(output)
                                }
                            }
                    }
                }
                ChangeType.REMOVED -> {
                    outFile.delete()
                }
            }
        }
    }
}
