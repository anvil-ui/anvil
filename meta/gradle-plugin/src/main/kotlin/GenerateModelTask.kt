package dev.inkremental.meta.gradle

import com.squareup.kotlinpoet.MemberName
import dev.inkremental.meta.model.*
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import java.io.File

abstract class GenerateModelTask : DefaultTask() {

    @get:Input abstract var quirks: InkrementalQuirks
    @get:Input abstract var camelCaseName: String
    @get:Input abstract var javadocContains: String
    @get:Input abstract var packageName: String
    @get:Input @get:Optional abstract var manualSetterName: String?
    @get:OutputFile abstract var outputFile: File

    abstract fun createIntrospector(): Introspector

    @TaskAction
    fun run() {
        val introspector = createIntrospector()
        val model = createModel(introspector)
        dumpModel(model)
        val json = ModelJson()
        val outputStr = json.stringify(ModuleModel.serializer(), model)
        outputFile.writeText(outputStr)
    }

    private fun createModel(introspector: Introspector): ModuleModel = ModuleModel(
        name = camelCaseName,
        javadocContains = javadocContains,
        packageName = packageName,
        manualSetter = manualSetterName?.let { MemberName(packageName, it) },
        views = introspector.provideViewModels(quirks)
    )

    private fun dumpModel(model: ModuleModel) {
        model.views.forEach { view ->
            logger.debug("[gen] ${view.plainType}")
            view.attrs.forEach {
                val t = it.type

                val flag = when {
                    it.isListener -> "L"
                    it.isNullable -> "F?"
                    else -> "F"
                }
                logger.debug("[gen]   ${it.name} | ${t.argType} | $flag")
                logger.debug("[gen]       plain   ${t.plainType}")
                t.lambdaType?.let { lambdaType ->
                    logger.debug("[gen]       lmbd    $lambdaType")
                }
                t.parametrizedTypeUnsafe?.let { parametrizedType ->
                    logger.debug("[gen]       prmtrzd $parametrizedType")
                }
            }
            logger.debug("[gen]")
        }
    }
}
